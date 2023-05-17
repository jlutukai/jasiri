package com.jasiri.data.mappers.auth

import com.jasiri.data.models.dtos.auth.LoginDto
import com.jasiri.data.models.dtos.auth.UserDto
import com.jasiri.domain.models.data_models.auth.LoginData
import com.jasiri.domain.models.data_models.auth.UserData

internal fun LoginDto.toDomain(): LoginData = LoginData(
    refreshToken = refreshToken,
    token = token,
    user = user.toDomain()
)

private fun UserDto.toDomain(): UserData = UserData(
    active = active,
    address = address,
    avatar = avatar,
    countryId  =countryId,
    createdAt = createdAt,
    createdBy = createdBy,
    deletedAt = deletedAt,
    deletedBy = deletedBy,
    dob  = dob,
    email = email,
    firstName  =firstName,
    gender = gender,
    id = id,
    lastName  =lastName,
    phoneNumber  =phoneNumber,
    phoneNumberAlt  =phoneNumberAlt,
    roleId  =roleId,
    schoolId = schoolId,
    updatedAt = updatedAt,
    updatedBy = updatedBy,
    userTypeId = userTypeId,
    username = username
)
