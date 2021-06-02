package com.cap0097.ahuahuapp.ui.result

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cap0097.ahuahuapp.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private val viewModel : ResultViewModel by viewModels()

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
        setLoading(true)
        val argsLat = arguments?.getString("LAT")
        val argsLong = arguments?.getString("LONG")
        Log.d("TAG_LAT", argsLat!!)
        Log.d("TAG_LONG", argsLong!!)
        viewModel.setResult(argsLat!!, argsLong!!).observe(viewLifecycleOwner, {
            Log.d("TAG_VM", it.toString())
            setLoading(false)
        })
    }

    private fun setLoading(state: Boolean){
        if(state){
            binding.apply {
                tvLabelPb.visibility = View.VISIBLE
                pbGetLocation.visibility = View.VISIBLE
                cardAir.visibility = View.GONE
                cardSmile.visibility = View.GONE
                tvAddress.visibility = View.GONE
                tvLabelRecomendation.visibility = View.GONE
            }
        }else{
            binding.apply {
                tvLabelPb.visibility = View.GONE
                pbGetLocation.visibility = View.GONE
                cardAir.visibility = View.VISIBLE
                cardSmile.visibility = View.VISIBLE
                tvAddress.visibility = View.VISIBLE
                tvLabelRecomendation.visibility = View.VISIBLE
            }
        }
    }
}