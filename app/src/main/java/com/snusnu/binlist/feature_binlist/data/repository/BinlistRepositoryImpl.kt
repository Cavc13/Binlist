package com.snusnu.binlist.feature_binlist.data.repository

import android.util.Log
import com.snusnu.binlist.feature_binlist.data.data_source.BinDao
import com.snusnu.binlist.feature_binlist.data.network.api.BinApi
import com.snusnu.binlist.feature_binlist.domain.model.Bin
import com.snusnu.binlist.feature_binlist.domain.repository.BinRepository
import kotlinx.coroutines.flow.Flow

class BinlistRepositoryImpl(
    private val dao: BinDao,
    private val api: BinApi
) : BinRepository {

    override fun getBinsFromRoom(): Flow<List<Bin>> {
        return dao.getBins()
    }

    override suspend fun getBinsFromNetwork(bin: String): Bin {
        val response = api.getBin(bin).body()

        return Bin(
            0,
            response?.scheme,
            response?.brand,
            response?.type,
            response?.prepaid ?: false,
            response?.countryDto?.name,
            response?.countryDto?.latitude,
            response?.countryDto?.longitude,
            response?.bankDto?.name,
            response?.bankDto?.city,
            response?.bankDto?.url,
            response?.bankDto?.phone,
            response?.numberDto?.length,
            response?.numberDto?.luhn ?: false
        )
    }

    override suspend fun insertBin(bin: Bin) {
        dao.insertBin(bin)
    }
}