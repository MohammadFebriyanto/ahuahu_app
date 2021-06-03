package com.cap0097.ahuahuapp.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cap0097.ahuahuapp.R
import com.cap0097.ahuahuapp.data.local.HistoryEntity
import com.cap0097.ahuahuapp.databinding.FragmentHomeBinding
import com.cap0097.ahuahuapp.domain.model.Result
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(), LocationListener {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var locationManager: LocationManager
    private lateinit var currentTime: String
    private val locationPermissionCode = 2
    private val viewModel: HomeViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutGetLocation.btnGetLocation.setOnClickListener {
            getCurrentShow(false)
            loadingShow(true)
            getLocation()
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText(requireContext(), "You have been turned on your GPS", Toast.LENGTH_LONG)
            .show()
    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText(
            requireContext(),
            "Please turn on your GPS before get current location",
            Toast.LENGTH_LONG
        ).show()
        loadingShow(false)
        getCurrentShow(true)
    }

    private fun getLocation() {
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    locationPermissionCode
                )
            }
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        }
    }

    override fun onLocationChanged(location: Location) {
        val lat = location.latitude.toString()
        val long = location.longitude.toString()
        viewModel.setResult(lat,long)
        viewModel.getResult().observe(viewLifecycleOwner, {
            binding.apply {
                loadingShow(false)
                getCurrentShow(false)
                resultShow(true, it)
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadingShow(state: Boolean) {
        if (state) {
            binding.layoutLoading.root.visibility = View.VISIBLE
        } else {
            binding.layoutLoading.root.visibility = View.GONE
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun resultShow(state: Boolean, result: Result? = null) {
        if (state) {
            if (result != null) {
                result.kualitasUdara.apply {
                    setRecommendation(this)
                }
                binding.layoutResult.apply {
                    tvLabelRecomendation.text = result.rekomendasi
                    tvAddress.text = result.label
                    tvLabelAir.text = "AIR QUALITY: ${result.kualitasUdara}"
                }
                currentTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val current = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss.SSS")
                    val formatted = current.format(formatter)
                    formatted.toString()
                } else {
                    val sdf = SimpleDateFormat("dd-M-yyyy | hh:mm:ss")
                    val currentDate = sdf.format(Date())
                    currentDate.toString()
                }
                val history = HistoryEntity(
                    null,
                    result.label.toString(),
                    result.kualitasUdara,
                    result.kualitasUdara,
                    currentTime,
                )
                viewModel.addHistory(history)
            }
            binding.layoutResult.root.visibility = View.VISIBLE
        } else {
            binding.layoutResult.root.visibility = View.GONE
        }
    }

    private fun getCurrentShow(state: Boolean) {
        if (state) {
            binding.layoutGetLocation.root.visibility = View.VISIBLE
        } else {
            binding.layoutGetLocation.root.visibility = View.GONE
        }
    }

    private fun setRecommendation(state: String) {
        when (state) {
            "BAIK" -> {
                binding.layoutResult.apply {
                    imgSmile.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.smile_green
                        )
                    )
                    tvLabelRecomendation.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }
            }
            "SEDANG" -> {
                binding.layoutResult.apply {
                    imgSmile.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.smile_green
                        )
                    )
                    tvLabelRecomendation.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }
            }
            "TIDAK SEHAT" -> {
                binding.layoutResult.apply {
                    imgSmile.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.smile_grey
                        )
                    )
                    tvLabelRecomendation.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey
                        )
                    )
                }
            }
            "SANGAT TIDAK SEHAT" -> {
                binding.layoutResult.apply {
                    imgSmile.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.smile_grey
                        )
                    )
                    tvLabelRecomendation.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey
                        )
                    )
                }
            }

            else -> {

                binding.layoutResult.apply {
                    imgSmile.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.smile_red
                        )
                    )
                    tvLabelRecomendation.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                }

            }
        }


    }
}
