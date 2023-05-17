package com.jasiri.domain.models.data_models.auth

data class UserData(
    val active: Boolean,
    val address: String?,
    val avatar: String?,
    val countryId: Int,
    val createdAt: String,
    val createdBy: Int,
    val deletedAt: String?,
    val deletedBy: Int?,
    val dob: String?,
    val email: String,
    val firstName: String,
    val gender: String?,
    val id: Int,
    val lastName: String,
    val phoneNumber: String,
    val phoneNumberAlt: String?,
    val roleId: Int,
    val schoolId: Int,
    val updatedAt: String,
    val updatedBy: Int,
    val userTypeId: Int,
    val username: String
)
