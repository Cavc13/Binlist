package com.snusnu.binlist.feature_binlist.domain.use_case

import com.snusnu.binlist.feature_binlist.domain.repository.BinRepository

class GetBinUseCase(
    private val repository: BinRepository
) {

    suspend operator fun invoke(binNumber: Int) = repository.getBinsFromNetwork(binNumber)
}