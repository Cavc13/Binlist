package com.snusnu.binlist.feature_binlist.domain.use_case

import com.snusnu.binlist.feature_binlist.domain.repository.BinRepository

class GetBinUseCase(
    private val repository: BinRepository
) {

    suspend operator fun invoke(binNumber: String) = repository.getBinsFromNetwork(binNumber)
}