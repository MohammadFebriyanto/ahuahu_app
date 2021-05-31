package com.cap0097.ahuahuapp.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.cap0097.ahuahuapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), LocationListener {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
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
        binding.btnGetLocation.setOnClickListener{
                getLocation()
            }
    }

    private fun getLocation() {
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode) }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    override fun onLocationChanged(location: Location) {
        Log.d("LOCATION", location.toString())
        Log.d("LATITUDE", location.latitude.toString())
        Log.d("LONGITUDE", location.longitude.toString())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
