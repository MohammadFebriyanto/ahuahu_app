package com.cap0097.ahuahuapp.domain.repository

import androidx.lifecycle.LiveData
import com.cap0097.ahuahuapp.data.local.HistoryEntity
import com.cap0097.ahuahuapp.domain.model.Result

interface Repository {
    fun getResult(lat: String, long: String) : LiveData<Result>
    suspend fun addHistory(history: HistoryEntity)
    fun getAllHistory() : LiveData<List<HistoryEntity>>
}