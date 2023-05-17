package com.jasiri.data.utils

import android.util.Log
import okhttp3.internal.and
import com.jasiri.data.sources.local.prefs_store.JasiriDataStore
import com.jasiri.domain.utils.DomainConstants.SECRET_KEY
import java.lang.Exception
import java.nio.charset.Charset
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptUtils @Inject constructor(
    private val dataStoreManager: JasiriDataStore
) {
    val PROVIDER = "BC"
    val SALT_LENGTH = 20
    val IV_LENGTH = 16
    val PBE_ITERATION_COUNT = 100

    private val RANDOM_ALGORITHM = "SHA1PRNG"
    private val HASH_ALGORITHM = "SHA-512"
    private val PBE_ALGORITHM = "PBEWithSHA256And256BitAES-CBC-BC"
    private val CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"
    val SECRET_KEY_ALGORITHM = "AES"
    private val TAG = "EncryptionPassword"

    fun decryptAndGetPassword(encryptedPassword: String, secretKey: String): String? {
        var passwrd: String? = ""
        if (encryptedPassword.isNotEmpty()) {
            try {
                val encoded = hexStringToByteArray(secretKey)
                val aesKey: SecretKey = SecretKeySpec(encoded, SECRET_KEY_ALGORITHM)
                passwrd = decrypt(aesKey, encryptedPassword)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return passwrd
    }

    suspend fun storePassword(password: String?): String? {
        var encryptedPassword: String? = ""
        if (password != null && password.isNotEmpty()) {
            val secretKey: SecretKey?
            try {
                secretKey = getSecretKey(password, generateSalt())
                val encoded: ByteArray = secretKey!!.encoded
                val input = byteArrayToHexString(encoded)
                dataStoreManager.putString(SECRET_KEY, input)
                encryptedPassword = encrypt(secretKey, password)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return encryptedPassword
    }

    @Throws(Exception::class)
    fun encrypt(secret: SecretKey?, cleartext: String): String? {
        return try {
            val iv = generateIv()
            val ivHex = byteArrayToHexString(iv)
            val ivspec = IvParameterSpec(iv)
            val encryptionCipher: Cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER)
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secret, ivspec)
            val encryptedText: ByteArray =
                encryptionCipher.doFinal(cleartext.toByteArray(charset("UTF-8")))
            val encryptedHex = byteArrayToHexString(encryptedText)
            ivHex + encryptedHex
        } catch (e: Exception) {
            Log.e("SecurityException", "${e.message}")
            throw Exception("Unable to encrypt", e)
        }
    }

    @Throws(Exception::class)
    fun decrypt(secret: SecretKey?, encrypted: String): String? {
        return try {
            val decryptionCipher: Cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER)
            val ivHex = encrypted.substring(0, IV_LENGTH * 2)
            val encryptedHex = encrypted.substring(IV_LENGTH * 2)
            val ivspec = IvParameterSpec(hexStringToByteArray(ivHex))
            decryptionCipher.init(Cipher.DECRYPT_MODE, secret, ivspec)
            val decryptedText: ByteArray =
                decryptionCipher.doFinal(hexStringToByteArray(encryptedHex))
            String(decryptedText, Charset.forName("UTF-8"))
        } catch (e: Exception) {
            Log.e("SecurityException", "${e.message}")
            throw Exception("Unable to decrypt", e)
        }
    }

    @Throws(Exception::class)
    fun generateSalt(): String? {
        return try {
            val random: SecureRandom = SecureRandom.getInstance(RANDOM_ALGORITHM)
            val salt = ByteArray(SALT_LENGTH)
            random.nextBytes(salt)
            byteArrayToHexString(salt)
        } catch (e: Exception) {
            throw Exception("Unable to generate salt", e)
        }
    }

    private fun byteArrayToHexString(b: ByteArray): String {
        val sb = StringBuffer(b.size * 2)
        for (i in b.indices) {
            val v: Int = b[i] and 0xff
            if (v < 16) {
                sb.append('0')
            }
            sb.append(Integer.toHexString(v))
        }
        return sb.toString().uppercase(Locale.getDefault())
    }

    private fun hexStringToByteArray(s: String?): ByteArray {
        val b = ByteArray(s!!.length / 2)
        for (i in b.indices) {
            val index = i * 2
            val v = s.substring(index, index + 2).toInt(16)
            b[i] = v.toByte()
        }
        return b
    }

    @Throws(Exception::class)
    fun getSecretKey(password: String, salt: String?): SecretKey? {
        return try {
            val pbeKeySpec = PBEKeySpec(
                password.toCharArray(),
                hexStringToByteArray(salt),
                PBE_ITERATION_COUNT,
                256
            )
            val factory: SecretKeyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM, PROVIDER)
            val tmp: SecretKey = factory.generateSecret(pbeKeySpec)
            SecretKeySpec(tmp.encoded, SECRET_KEY_ALGORITHM)
        } catch (e: Exception) {
            throw Exception("Unable to get secret key", e)
        }
    }

    @Throws(NoSuchAlgorithmException::class, NoSuchProviderException::class)
    private fun generateIv(): ByteArray {
        val random: SecureRandom = SecureRandom.getInstance(RANDOM_ALGORITHM)
        val iv = ByteArray(IV_LENGTH)
        random.nextBytes(iv)
        return iv
    }
}