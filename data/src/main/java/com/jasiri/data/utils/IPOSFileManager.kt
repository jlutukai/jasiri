package com.jasiri.data.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class JasiriFileManager @Inject constructor(@ApplicationContext private val context: Context) {
    fun saveFile(body: ResponseBody, filename: String): File? {
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val path: String = context.filesDir.absolutePath
            val file = File(path, filename)
            val fos = FileOutputStream(file.path)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return file
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            input?.close()
        }
        return null
    }
}