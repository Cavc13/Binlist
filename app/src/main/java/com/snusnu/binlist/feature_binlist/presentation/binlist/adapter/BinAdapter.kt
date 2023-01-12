package com.snusnu.binlist.feature_binlist.presentation.binlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.snusnu.binlist.databinding.ItemBinBinding
import com.snusnu.binlist.feature_binlist.domain.model.Bin
import com.snusnu.binlist.feature_binlist.presentation.utils.ANSWER_NO
import com.snusnu.binlist.feature_binlist.presentation.utils.EMPTY
import com.snusnu.binlist.feature_binlist.presentation.utils.ANSWER_YES

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
                tvScheme.text = scheme ?: EMPTY
                tvType.text = type ?: EMPTY
                tvBrand.text = brand ?: EMPTY
                tvPrepaid.text = if(prepaid) ANSWER_YES else ANSWER_NO
                tvLength.text = "$length"
                tvLuhn.text = if(luhn) ANSWER_YES else ANSWER_NO
                tvCountry.text = country
                tvLatitude.text = if (latitude !=null) "$latitude" else EMPTY
                tvLongitude.text = if (longitude !=null) "$longitude" else EMPTY
                tvBank.text = if (bankName != null || bankCity != null)
                    "$bankName, $bankCity"
                    else "$EMPTY, $EMPTY"
                tvUrl.text = url ?: EMPTY
                tvPhone.text = phone ?: EMPTY
            }
        }
    }
}