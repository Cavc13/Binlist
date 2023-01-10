package com.snusnu.binlist.feature_binlist.data.network.dto

data class CountryDto(
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Int,
    val longitude: Int,
    val name: String,
    val numeric: String
)