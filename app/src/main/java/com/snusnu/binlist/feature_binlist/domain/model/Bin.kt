package com.snusnu.binlist.feature_binlist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bin(
    @PrimaryKey
    val id: Int? = null,
    val scheme: String? = null,
    val brand: String? = null,
    val type: String? = null,
    val prepaid: String? = null,
    val country: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
    val bank: String? = null
)
