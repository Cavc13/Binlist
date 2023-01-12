package com.snusnu.binlist.feature_binlist.presentation.home

import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.InputFilter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snusnu.binlist.feature_binlist.domain.model.Bin
import com.snusnu.binlist.feature_binlist.domain.use_case.BinUseCases
import com.snusnu.binlist.feature_binlist.presentation.utils.nonDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val binUseCases: BinUseCases
) : ViewModel() {

    private val _binFlow = MutableStateFlow(Bin())
    val binFlow: StateFlow<Bin>
        get() = _binFlow

    private var current = ""

    fun watchListener(s: Editable?) {
        if (s.toString() != current) {
            val userInput = s.toString().replace(nonDigits,"")
            if (userInput.length <= 8) {
                current = userInput.chunked(4).joinToString(" ")
                s?.filters = arrayOfNulls<InputFilter>(0)
            }
            s?.replace(0, s.length, current, 0, current.length)
        }
    }

    fun searchAndAddBank(bin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val bin = binUseCases.getBin(bin)
            _binFlow.value = bin
            binUseCases.addBin(bin)
        }
    }
}