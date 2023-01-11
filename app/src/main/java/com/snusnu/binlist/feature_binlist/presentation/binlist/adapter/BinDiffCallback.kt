package com.snusnu.binlist.feature_binlist.presentation.binlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.snusnu.binlist.feature_binlist.domain.model.Bin

object BinDiffCallback : DiffUtil.ItemCallback<Bin>() {
    override fun areItemsTheSame(oldItem: Bin, newItem: Bin): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Bin, newItem: Bin): Boolean {
        return oldItem == newItem
    }
}