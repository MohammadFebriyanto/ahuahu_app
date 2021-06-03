package com.cap0097.ahuahuapp.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cap0097.ahuahuapp.R
import com.cap0097.ahuahuapp.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { HistoryAdapter() }
    private val viewModel : HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        viewModel.getAllHistory().observe(viewLifecycleOwner, {
            adapter.setHistoryData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRv() {
        with(binding) {
            rvHistory.adapter = adapter
            rvHistory.layoutManager = LinearLayoutManager(requireContext())
            rvHistory.setHasFixedSize(true)
        }
    }
}