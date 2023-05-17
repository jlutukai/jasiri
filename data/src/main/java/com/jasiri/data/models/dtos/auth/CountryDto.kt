package com.jasiri.data.models.dtos.auth

data class CountryDto(
    val createdAt: String,
    val createdBy: Int,
    val currencyCode: String,
    val deletedAt: String,
    val deletedBy: Int?,
    val id: Int,
    val iso2CountryCode: String,
    val iso3CountryCode: String,
    val languages: List<String>,
    val name: String,
    val phoneCode: String,
    val status: Boolean,
    val timezone: String,
    val updatedAt: String,
    val updatedBy: Int
)