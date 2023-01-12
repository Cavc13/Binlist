package com.snusnu.binlist.feature_binlist.data.network.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("bank")
    val bankDto: BankDto,
    val brand: String,
    @SerializedName("country")
    val countryDto: CountryDto,
    @SerializedName("number")
    val numberDto: NumberDto,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)