package com.jasiri.data.sources.remote.interceptors

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class NoContentInterceptor : Interceptor {

    private val noContentHttpStatusCodes = arrayOf(204, 205)

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            return response
        }

        if (!noContentHttpStatusCodes.contains(response.code)) {
            return response
        }

        return response
            .newBuilder()
            .code(200)
            .body("".toResponseBody("text/plain".toMediaType()))
            .build()
    }
}