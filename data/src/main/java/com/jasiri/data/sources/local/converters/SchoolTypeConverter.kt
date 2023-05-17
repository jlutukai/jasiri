package com.jasiri.data.sources.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasiri.domain.models.data_models.schools.SchoolData
import java.io.Serializable
import java.lang.reflect.Type

class SchoolTypeConverter: Serializable {
    @TypeConverter
    fun fromObjectToString(schoolData: SchoolData?): String? {
        val gson = Gson()
        val type = object : TypeToken<SchoolData>() {}.type
        return gson.toJson(schoolData, type)
    }

    @TypeConverter
    fun fromStringToObject(schoolData: String?): SchoolData? {
        val gson = Gson()
        val type: Type = object : TypeToken<SchoolData>() {}.type
        return gson.fromJson(schoolData, type)
    }
}