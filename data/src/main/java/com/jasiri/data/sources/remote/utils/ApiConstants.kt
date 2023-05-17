package com.jasiri.data.sources.remote.utils

object ApiConstants {
    const val START_PAGE = 1
    const val PAGE_SIZE = 15
    const val PREFETCH_DISTANCE = 7
    const val HTTP_UNAUTHORIZED = 401
    const val HTTP_SUCCESS = 200
    const val REFRESH_TOKEN_URL = "BuildConfig.BASE_URL" + "/api/v1/auth/refresh-token"
}

object ApiParameters {
    const val TOKEN_TYPE = "Bearer "
    const val AUTH_HEADER = "Authorization"

    const val NO_AUTH_HEADER_KEY = "No-Auth-Header"
    private const val NO_AUTH_VALUE = ": No-Authorization"
    const val NO_AUTH_HEADER = NO_AUTH_HEADER_KEY + NO_AUTH_VALUE
}