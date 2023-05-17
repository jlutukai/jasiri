package com.jasiri.data.sources.local.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import com.jasiri.data.UserToken

class UserTokenSerializer @Inject constructor() : Serializer<UserToken> {
    override val defaultValue: UserToken
        get() = UserToken.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserToken =
        try {
            UserToken.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            Timber.e("Unable to read proto.", exception)
            throw CorruptionException("Unable to read proto.", exception)
        }

    override suspend fun writeTo(t: UserToken, output: OutputStream) {
        t.writeTo(output)
    }
}