package com.cap0097.ahuahuapp.data

import androidx.lifecycle.LiveData
import com.cap0097.ahuahuapp.data.local.HistoryDao
import com.cap0097.ahuahuapp.data.local.HistoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val historyDao: HistoryDao) {
    fun getAllHistory() : LiveData<List<HistoryEntity>> = historyDao.getAllHistory()

    suspend fun addHistory(history: HistoryEntity){
        historyDao.addHistory(history)
    }
}