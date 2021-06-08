package com.cap0097.ahuahuapp.ui.result

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.cap0097.ahuahuapp.R
import com.cap0097.ahuahuapp.data.local.HistoryEntity
import com.cap0097.ahuahuapp.databinding.ActivityResultBinding
import com.cap0097.ahuahuapp.domain.model.Result
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var currentTime: String
    private val viewModel: ResultViewModel by viewModels()

    companion object {
        const val EXTRA_LONG = "extra_long"
        const val EXTRA_LAT = "extra_lat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingShow(true)
        resultShow(false)
        binding.include.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            setNavigationOnClickListener {
                finish()
            }
        }
        val lat = intent.getStringExtra(EXTRA_LAT)
        val long = intent.getStringExtra(EXTRA_LONG)
        viewModel.setResult(lat!!, long!!)
        viewModel.getResult().observe(this, {
            binding.apply {
                loadingShow(false)
                resultShow(true, it)
            }
        })
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun resultShow(state: Boolean, result: Result? = null) {
        if (state) {
            if (result != null) {
                binding.layoutResult.apply {
                    tvLabelRecomendation.text = result.rekomendasi
                    tvAddress.text = result.label
                    tvLabelAir.text = "AIR QUALITY: ${result.kualitasUdara}"
                    tvDesc.text = result.desc.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                    Glide.with(this@ResultActivity)
                        .load(result.link)
                        .placeholder(R.drawable.logo_placeholder)
                        .into(imgSmile)
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

    private fun loadingShow(state: Boolean) {
        if (state) {
            binding.layoutLoading.root.visibility = View.VISIBLE
        } else {
            binding.layoutLoading.root.visibility = View.GONE
        }
    }
}