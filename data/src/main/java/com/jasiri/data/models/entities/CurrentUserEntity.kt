package com.jasiri.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jasiri.data.models.entities.CurrentUserEntity.Companion.CURRENT_USER_TABLE
import com.jasiri.data.sources.local.converters.CountryTypeConverter
import com.jasiri.data.sources.local.converters.SchoolTypeConverter
import com.jasiri.domain.models.data_models.auth.CountryData
import com.jasiri.domain.models.data_models.schools.SchoolData

@TypeConverters(CountryTypeConverter::class, SchoolTypeConverter::class)

@Entity(tableName = CURRENT_USER_TABLE)
data class CurrentUserEntity(
    val active: Boolean,
    val address: String,
    val avatar: String,
    val country: CountryData?,
    val countryId: Int,
    val createdAt: String,
    val createdBy: Int,
    val deletedAt: String,
    val deletedBy: Int?,
    val dob: String,
    val email: String,
    val firstName: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val lastName: String,
    val phoneNumber: String,
    val phoneNumberAlt: String,
    val roleId: Int,
    val school: SchoolData?,
    val schoolId: Int,
    val updatedAt: String,
    val updatedBy: Int,
    val userTypeId: Int,
    val username: String
){
    companion object {
        const val CURRENT_USER_TABLE = "current_user"
    }
}
