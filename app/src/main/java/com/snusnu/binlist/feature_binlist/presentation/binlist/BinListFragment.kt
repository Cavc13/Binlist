package com.snusnu.binlist.feature_binlist.presentation.binlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.snusnu.binlist.databinding.FragmentBinListBinding
import com.snusnu.binlist.feature_binlist.presentation.binlist.adapter.BinAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BinListFragment : Fragment() {

    private var _binding: FragmentBinListBinding? = null
    private val binding get() = _binding!!

    private val binListViewModel: BinListViewModel by viewModels()
    private lateinit var binAdapter: BinAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBinListBinding.inflate(inflater, container, false)

        binAdapter = BinAdapter()

        binding.rvBinList.adapter = binAdapter

        lifecycleScope.launch(Dispatchers.Main) {
            binListViewModel.binListFlow.collect{
                binAdapter.submitList(it)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}