package com.jasiri.data.sources.local

import com.jasiri.data.sources.local.prefs_store.JasiriDataStore
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStoreManager: JasiriDataStore
) : LocalDataSource {
    // permissions

}
