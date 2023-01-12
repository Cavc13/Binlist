package com.snusnu.binlist.feature_binlist.presentation.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.snusnu.binlist.databinding.FragmentHomeBinding
import com.snusnu.binlist.feature_binlist.domain.model.Bin
import com.snusnu.binlist.feature_binlist.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
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

        setupStartBin()
        binTextChangedListener()
        setClickListeners()
    }

    private fun setupStartBin() {
        lifecycleScope.launch(Dispatchers.Main) {
            homeViewModel.binFlow.collect {
                setBankParams(it)
            }
        }
    }

    private fun setClickListeners() {
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
                        setBankParams(it)
                    }
                }
            }
        }

        binding.tvUrl.setOnClickListener {
            createIntentBrowser(binding.tvUrl.text.toString())
        }
        binding.tvPhone.setOnClickListener {
            checkPhonePermissionAndMakeCall(binding.tvPhone.text.toString())
        }
        binding.cvCountry.setOnClickListener {
            openMap(binding.tvLatitude.text.toString(), binding.tvLongitude.text.toString())
        }
    }

    private fun setBankParams(bin: Bin) {
        with(binding) {
            with(bin) {
                tvScheme.text = scheme ?: EMPTY
                tvType.text = type ?: EMPTY
                tvBrand.text = brand ?: EMPTY
                tvPrepaid.text =
                    if (luhn) resources.getString(com.snusnu.binlist.R.string.text_bold_yes_no)
                    else resources.getString(com.snusnu.binlist.R.string.text_yes_bold_no)
                tvLength.text = "$length"
                tvLuhn.text =
                    if (luhn) resources.getString(com.snusnu.binlist.R.string.text_bold_yes_no)
                    else resources.getString(com.snusnu.binlist.R.string.text_yes_bold_no)
                tvCountry.text = country ?: EMPTY
                tvLatitude.text = if (latitude != null) "$latitude" else EMPTY
                tvLongitude.text = if (longitude != null) "$longitude" else EMPTY
                tvBank.text = if (bankName != null || bankCity != null)
                    "$bankName, $bankCity"
                else "$EMPTY, $EMPTY"
                tvUrl.text = url ?: EMPTY
                tvPhone.text = phone ?: EMPTY
            }
        }
    }

    private fun openMap(latitude: String, longitude: String) {
        if (latitude != EMPTY && longitude != EMPTY) {
            val geoUri = Uri.parse("$GEO$latitude,$longitude")
            val geoIntent = Intent(Intent.ACTION_VIEW, geoUri)
            startActivity(geoIntent)
        }
    }

    private fun binTextChangedListener() {
        binding.etBin.addTextChangedListener { s ->
            homeViewModel.watchListener(s)
        }
    }

    private fun createIntentBrowser(url: String) {
        if (url != EMPTY) {
            val address = Uri.parse("$HTTPS$url")
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                .setData(address)
            startActivity(openLinkIntent)
        }
    }

    private fun createIntentCall(phone: String) {
        if (phone != EMPTY) {
            val numberPhone = Uri.parse("$TELEPHONE$phone")
            val telephoneIntent = Intent(Intent.ACTION_CALL, numberPhone)
            startActivity(telephoneIntent)
        }
    }

    private fun checkPhonePermissionAndMakeCall(phone: String) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                PHONE_PERMISSION_CODE
            )
        } else {
            createIntentCall(phone)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == PHONE_PERMISSION_CODE) {
            val numberPhone = binding.tvPhone.text.toString()
            if (numberPhone != EMPTY) {
                val numberPhone = Uri.parse("$TELEPHONE$numberPhone")
                val telephoneIntent = Intent(Intent.ACTION_CALL, numberPhone)
                startActivity(telephoneIntent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PHONE_PERMISSION_CODE = 51
    }
}