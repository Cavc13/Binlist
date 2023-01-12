package com.snusnu.binlist.feature_binlist.presentation.binlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snusnu.binlist.feature_binlist.domain.model.Bin
import com.snusnu.binlist.feature_binlist.domain.use_case.BinUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinListViewModel @Inject constructor(
    private val binUseCases: BinUseCases
) : ViewModel() {

    private val _binListFlow = MutableStateFlow<List<Bin>>(emptyList())
    val binListFlow: StateFlow<List<Bin>>
        get() = _binListFlow

    init {
        getBinList()
    }

    fun getBinList() {
        viewModelScope.launch(Dispatchers.IO) {
            binUseCases.getBinList.invoke().collectLatest {
                _binListFlow.value = it.reversed()
            }
        }
    }
}