package com.snusnu.binlist.feature_binlist.domain.use_case

data class BinUseCases(
    val getBinList: GetBinListUseCase,
    val addBin: AddBinUseCase,
    val getBin: GetBinUseCase
)