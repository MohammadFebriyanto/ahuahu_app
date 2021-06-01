package com.cap0097.ahuahuapp.ui.result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cap0097.ahuahuapp.R
import com.cap0097.ahuahuapp.databinding.FragmentHomeBinding
import com.cap0097.ahuahuapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    companion object{
        const val EXTRA_LAT_LONG = "extra_lat_long"
    }

    private var _binding : FragmentResultBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val latLong = activity?.intent?.getStringExtra(EXTRA_LAT_LONG)
        val args = arguments?.getString("LAT_LONG")
        setLoading(true);
    }

    private fun setLoading(state: Boolean){
        if(state){
            binding.apply {
                tvLabelPb.visibility = View.VISIBLE
                pbGetLocation.visibility = View.VISIBLE
            }
        }else{
            binding.apply {
                tvLabelPb.visibility = View.GONE
                pbGetLocation.visibility = View.GONE
            }
        }
    }
}