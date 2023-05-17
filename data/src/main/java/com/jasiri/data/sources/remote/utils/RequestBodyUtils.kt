package com.jasiri.data.sources.remote.utils

import okhttp3.RequestBody

fun RequestBody?.bodyToString(): String {
    if (this == null) return ""
    val buffer = okio.Buffer()
    writeTo(buffer)
    return buffer.readUtf8()
}