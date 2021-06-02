package com.cap0097.ahuahuapp.domain.repository

import androidx.lifecycle.LiveData
import com.cap0097.ahuahuapp.domain.model.Result

interface NetworkRepository {
    fun getResult(lat: String, long: String) : LiveData<Result>
}