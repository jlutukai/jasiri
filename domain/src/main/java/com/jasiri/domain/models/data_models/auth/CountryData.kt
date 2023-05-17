package com.jasiri.domain.models.data_models.auth

data class CountryData(
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
