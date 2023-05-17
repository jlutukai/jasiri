package com.jasiri.domain.utils

import java.util.*

object DomainConstants {

    const val SYNCED_STATUS = 0
    const val UN_SYNCED_STATUS = 1
    const val PER_PAGE = "perPage"
    const val PAGE = "page"
    const val INCLUDE = "include"
    const val CUSTOMERS = "customers"
    const val LAST_UPDATED_AT = "lastUpdatedAt"


    fun getCurrentTimeInUnix(): Long {
        return Calendar.getInstance().timeInMillis
    }

    fun getUUID(): String {
        return UUID.randomUUID().toString()
    }


    const val REFRESH_TOKEN = "refresh_token"
    const val SECRET_KEY = "secret_key"
    const val TOKEN_KEY = "TOKEN_KEY"
    const val IS_FIRST_SESSION = "is_firstTime"


}