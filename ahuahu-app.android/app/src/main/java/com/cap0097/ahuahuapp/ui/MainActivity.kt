package com.cap0097.ahuahuapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cap0097.ahuahuapp.R
import com.cap0097.ahuahuapp.databinding.ActivityMainBinding
import com.cap0097.ahuahuapp.ui.about.AboutFragment
import com.cap0097.ahuahuapp.ui.history.HistoryFragment
import com.cap0097.ahuahuapp.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addFragment(HomeFragment())

        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->{
                    addFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_history->{
                    addFragment(HistoryFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_about->{
                    addFragment(AboutFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.container, fragment)
            .commit()
    }
}