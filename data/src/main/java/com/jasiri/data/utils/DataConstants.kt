package com.jasiri.data.utils

import java.util.*

object DataConstants {
    const val SYNCED_STATUS = 0
    const val UN_SYNCED_STATUS = 1
    const val PER_PAGE = "perPage"
    const val PAGE = "page"
    const val INCLUDE = "include"
    const val CUSTOMERS = "customers"
    const val LAST_UPDATED_AT = "lastUpdatedAt"
    const val DEFAULT_PAGE_SIZE = 50
    const val DEFAULT_PAGE = 1
    const val DEFAULT_MAX_PAGE_SIZE = 50000
    const val DEFAULT_CHUNK_PAGE_SIZE = 20
    const val DEFAULT_CHUNK_PAGE_SIZE_WORKERS = 50
    const val DEFAULT_30_DAYS = 30
    const val DEFAULT_5_DAYS = 5
    const val DEFAULT_1_DAY = 1



    val PREFS_NAME = "com.jasiri.erp.AppPreferences"
    val TOKEN_KEY = "TOKEN_KEY"
    const val IMAGE_PART_NAME = "file"
    const val STORE_NAME = "com.jasiri.erp.preferences_store"



    fun getUUID(): String {
        return UUID.randomUUID().toString()
    }
}