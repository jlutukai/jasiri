package com.jasiri.domain.models.data_models.schools

data class SchoolData(
    val active: Boolean,
    val address: String,
    val boardingStatus: String,
    val code: String,
    val countryId: Int,
    val createdAt: String,
    val curriculumId: Int,
    val deletedAt: String,
    val deletedBy: Int?,
    val deputies: List<DeputyData>,
    val deputyUserIds: List<Int>,
    val email: String,
    val id: Int,
    val levelId: Int,
    val logo: String,
    val name: String,
    val phoneNumber: String,
    val principalUserId: Int,
    val regionId: Int,
    val schoolType: String,
    val updatedAt: String,
    val updatedBy: Int
)
