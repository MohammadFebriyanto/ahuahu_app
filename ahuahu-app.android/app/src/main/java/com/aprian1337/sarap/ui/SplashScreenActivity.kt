package com.aprian1337.sarap.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aprian1337.sarap.R

class SplashScreenActivity : AppCompatActivity() {
    private val splashTime: Long = 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                Intent(this@SplashScreenActivity, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }, splashTime
        )
    }
}