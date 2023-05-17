package com.jasiri.data.sources.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasiri.domain.models.data_models.auth.CountryData
import java.io.Serializable
import java.lang.reflect.Type

class CountryTypeConverter: Serializable {
    @TypeConverter
    fun fromObjectToString(countryData: CountryData?): String? {
        val gson = Gson()
        val type = object : TypeToken<CountryData>() {}.type
        return gson.toJson(countryData, type)
    }

    @TypeConverter
    fun fromStringToObject(countryData: String?): CountryData? {
        val gson = Gson()
        val type: Type = object : TypeToken<CountryData>() {}.type
        return gson.fromJson(countryData, type)
    }
}