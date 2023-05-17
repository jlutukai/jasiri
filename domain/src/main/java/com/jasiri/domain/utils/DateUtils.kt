package com.jasiri.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun parseJsonDate(jsonDate: String): Date? {
        val sdf = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        ).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return try {
            sdf.parse(jsonDate)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun toServerFormat(timeInMillis: Long): String {
        return try {
            val outFormat = SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
            )
            outFormat.format(timeInMillis)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
    fun toServerFormatDateTime(timeInMillis: Long): String {
        return try {
            val outFormat = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()
            )
            outFormat.format(timeInMillis)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun toUnixTimeStamp(dateTime: String): Long {
        return try {
            val outFormat = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()
            )
            outFormat.parse(dateTime).time
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    fun String.getUnixTimeFromJsonDate(): Long {
        return try {
            val outFormat = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()
            )
            outFormat.parse(this)?.time ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    fun String.shortDateToUnixTimeStamp(): Long {
        return try {
            val outFormat = SimpleDateFormat(
                "yyyy-MM-dd",
                Locale.getDefault()
            )
            outFormat.parse(this)?.time ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    fun String.getUnixTimeFromJsonDateWithZone(): Long {
        return try {
            val outFormat = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
                Locale.getDefault()
            )
            outFormat.parse(this)?.time ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    fun getCurrentTimeInUnix(): Long {
        return Calendar.getInstance().timeInMillis
    }

//    fun Long.isYesterday() =
//        DateUtils.isToday(this + DateUtils.DAY_IN_MILLIS)

    fun getTimeLastNDaysUpdateTime(n: Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -n)
        return toServerFormatDateTime(cal.timeInMillis)
    }

    fun getDefaultTimeUpdates(): String {
        return toServerFormatDateTime(0)
    }

    fun addNNumberOfDays(timeInMillis: Long, noOfDays: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.time = Date(timeInMillis)
        calendar.add(Calendar.DAY_OF_MONTH, noOfDays)
        return calendar.timeInMillis
    }

    fun getStartOfDay(timeInMillis: Long): Long {
        val cal = Calendar.getInstance()
        cal.time = Date(timeInMillis)
        cal[Calendar.HOUR_OF_DAY] = 0
        cal.clear(Calendar.MINUTE)
        cal.clear(Calendar.SECOND)
        cal.clear(Calendar.MILLISECOND)
        return cal.timeInMillis / 1000
    }
    fun getEndOfDay(timeInMillis: Long): Long {
        val cal = Calendar.getInstance()
        cal.time = Date(timeInMillis)
        cal[Calendar.HOUR_OF_DAY] = 23
        cal[Calendar.MINUTE] = 59
        cal[Calendar.SECOND] = 59
        cal[Calendar.MILLISECOND] = 999
        return cal.timeInMillis / 1000
    }
}