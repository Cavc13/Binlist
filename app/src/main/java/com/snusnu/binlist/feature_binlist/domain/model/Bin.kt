package com.snusnu.binlist.feature_binlist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bin(
    @PrimaryKey(autoGenerate = true)
    val id: Long = UNDEFINED_ID,
    val scheme: String? = null,
    val brand: String? = null,
    val type: String? = null,
    var prepaid: Boolean = false,
    val country: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
    val bankName: String? = null,
    val bankCity: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val length: Int? = null,
    var luhn: Boolean = false
) {
    companion object {
        private const val UNDEFINED_ID = 0L
    }
}
