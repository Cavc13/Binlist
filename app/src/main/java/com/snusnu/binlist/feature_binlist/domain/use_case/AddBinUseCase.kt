package com.snusnu.binlist.feature_binlist.domain.use_case

import com.snusnu.binlist.feature_binlist.domain.model.Bin
import com.snusnu.binlist.feature_binlist.domain.repository.BinRepository

class AddBinUseCase(
    private val repository: BinRepository
) {

    suspend operator fun invoke(bin: Bin){
        repository.insertBin(bin)
    }
}