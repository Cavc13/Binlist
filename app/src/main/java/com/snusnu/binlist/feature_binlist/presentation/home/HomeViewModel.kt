package com.snusnu.binlist.feature_binlist.presentation.home

import androidx.lifecycle.ViewModel
import com.snusnu.binlist.feature_binlist.domain.use_case.BinUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val binUseCases: BinUseCases
) : ViewModel() {

}