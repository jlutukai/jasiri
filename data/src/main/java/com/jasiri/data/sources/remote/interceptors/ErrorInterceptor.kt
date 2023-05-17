package com.jasiri.data.sources.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import com.jasiri.data.sources.remote.utils.ApiConstants.HTTP_UNAUTHORIZED
import com.jasiri.data.sources.remote.utils.ApiConstants.REFRESH_TOKEN_URL
import com.jasiri.data.sources.remote.utils.bodyToString
import timber.log.Timber


class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val isRefreshTokenUrl = request.url.toString() == REFRESH_TOKEN_URL
        if (!response.isSuccessful && response.code != HTTP_UNAUTHORIZED && !isRefreshTokenUrl) {
            Timber.tag(
                "NETWORK_REQUEST_ERROR1"
            ).e(
                """
                    {
                        request: {
                            "url": "${request.url}",
                            "method": ${request.method},
                            "payload": ${request.body.bodyToString()},
                            "headers": ${request.headers.names()}
                        },
                        response: {
                            "body": ${response.peekBody(Long.MAX_VALUE).string()},
                            "code": ${response.code}
                        }
                    }
                """.trimIndent()
            )
        }
        return response
    }
}