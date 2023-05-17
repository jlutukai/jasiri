package com.jasiri.erp.utils.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun getConnectionStatus(): Flow<ConnectionState>

    val currentConnectivityState: ConnectionState
}