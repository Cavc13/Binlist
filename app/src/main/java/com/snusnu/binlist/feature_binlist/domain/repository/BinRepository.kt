package com.snusnu.binlist.feature_binlist.domain.repository

import com.snusnu.binlist.feature_binlist.domain.model.Bin
import kotlinx.coroutines.flow.Flow

interface BinRepository {

    fun getBins(): Flow<List<Bin>>

    suspend fun insertBin(bin: Bin)
}