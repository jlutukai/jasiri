package com.jasiri.data.sources.remote.interceptors

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import com.jasiri.data.sources.remote.utils.ApiParameters.AUTH_HEADER
import com.jasiri.data.sources.remote.utils.ApiParameters.NO_AUTH_HEADER_KEY
import com.jasiri.data.sources.remote.utils.ApiParameters.TOKEN_TYPE
import com.jasiri.data.utils.DataConstants.TOKEN_KEY
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val prefs: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedRequest = chain.request()
        if (chain.request().headers[NO_AUTH_HEADER_KEY] != null) {
            return chain.proceed(interceptedRequest)
        }
        val token = prefs.getString(TOKEN_KEY, null)
        return if (!token.isNullOrEmpty()) {
            val newRequest = interceptedRequest.newBuilder().header(
                AUTH_HEADER, TOKEN_TYPE + token
            ).build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(interceptedRequest)
        }
    }
}