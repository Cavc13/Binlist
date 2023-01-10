package com.snusnu.binlist.feature_binlist.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snusnu.binlist.feature_binlist.data.repository.BinlistRepositoryImpl
import com.snusnu.binlist.feature_binlist.domain.use_case.BinUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val binUseCases: BinUseCases
) : ViewModel() {

}