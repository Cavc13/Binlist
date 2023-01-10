package com.snusnu.binlist.feature_binlist.data.network.dto

data class ResponseDto(
    val bankDto: BankDto,
    val brand: String,
    val countryDto: CountryDto,
    val numberDto: NumberDto,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)