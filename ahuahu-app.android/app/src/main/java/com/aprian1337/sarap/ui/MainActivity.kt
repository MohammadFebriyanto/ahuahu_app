package com.aprian1337.sarap.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aprian1337.sarap.R
import com.aprian1337.sarap.databinding.ActivityMainBinding
import com.aprian1337.sarap.ui.about.AboutFragment
import com.aprian1337.sarap.ui.history.HistoryFragment
import com.aprian1337.sarap.ui.home.HomeFragment

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