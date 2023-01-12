package com.snusnu.binlist.feature_binlist.domain.use_case

import com.snusnu.binlist.feature_binlist.domain.repository.BinRepository

class GetBinListUseCase(
    private val repository: BinRepository
) {

    operator fun invoke() = repository.getBinsFromRoom()
}