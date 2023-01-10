package com.snusnu.binlist.feature_binlist.data.repository

import com.snusnu.binlist.feature_binlist.data.data_source.BinDao
import com.snusnu.binlist.feature_binlist.domain.model.Bin
import com.snusnu.binlist.feature_binlist.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow

class BinlistRepositoryImpl(
    private val dao: BinDao
) : BinRepository {

    override fun getBins(): Flow<List<Bin>> {
        return dao.getBins()
    }

    override suspend fun insertBin(bin: Bin) {
        dao.insertBin(bin)
    }
}