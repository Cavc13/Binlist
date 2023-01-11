package com.snusnu.binlist.feature_binlist.presentation.binlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.snusnu.binlist.R
import com.snusnu.binlist.databinding.ItemBinBinding
import com.snusnu.binlist.feature_binlist.domain.model.Bin

class BinAdapter : ListAdapter<Bin, BinViewHolder>(BinDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
        return BinViewHolder(
            ItemBinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
        val currentBin = getItem(position)

        with(holder.binding) {
            with(currentBin) {
                tvScheme.text = scheme ?: "?"
                tvType.text = type ?: "?"
                tvBrand.text = brand ?: "?"
                tvPrepaid.text = if(prepaid) "YES" else "NO"
                tvLength.text = "$length"
                tvLuhn.text = if(luhn) "YES" else "NO"
                tvCountry.text = country
                tvLatitudeLongitude.text = if (longitude !=null || latitude != null)
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