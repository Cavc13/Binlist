package com.snusnu.binlist.feature_binlist.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.snusnu.binlist.R
import com.snusnu.binlist.databinding.FragmentHomeBinding
import com.snusnu.binlist.feature_binlist.presentation.utils.ERROR_EMPTY_TEXT
import com.snusnu.binlist.feature_binlist.presentation.utils.ERROR_WRONG_TEXT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binTextChangedListener()
        setClickListener()
    }

    private fun setClickListener() {
        binding.ivSearch.setOnClickListener {
            if (binding.etBin.text.isNullOrBlank()) {
                binding.etBin.error = ERROR_EMPTY_TEXT
            } else if (binding.etBin.text.toString().length < 9) {
                binding.etBin.error = ERROR_WRONG_TEXT
            } else {
                val bin = binding.etBin.text.toString().replace(" ", "")
                homeViewModel.searchAndAddBank(bin)

                lifecycleScope.launch(Dispatchers.Main) {
                    homeViewModel.binFlow.collect {
                        with(binding) {
                            with(it) {
                                tvScheme.text = scheme ?: "?"
                                tvType.text = type ?: "?"
                                tvBrand.text = brand ?: "?"
                                tvPrepaid.text =
                                    if (luhn) resources.getString(R.string.text_bold_yes_no)
                                    else resources.getString(R.string.text_yes_bold_no)
                                tvLength.text = "$length"
                                tvLuhn.text =
                                    if (luhn) resources.getString(R.string.text_bold_yes_no)
                                    else resources.getString(R.string.text_yes_bold_no)
                                tvCountry.text = country
                                tvLatitudeLongitude.text =
                                    if (longitude != null || latitude != null)
                                        "(latitude: $latitude, longitude: $longitude)"
                                    else "(latitude: ?, longitude: ?)"
                                tvBank.text = if (bankName != null || bankCity != null)
                                    "$bankName, $bankCity"
                                else "?, ?"
                                tvUrl.text = url ?: "?"
                                tvPhone.text = phone ?: "?"
                            }
                        }
                    }
                }
            }
        }
    }

    private fun binTextChangedListener() {
        binding.etBin.addTextChangedListener { s ->
            homeViewModel.watchListener(s)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}