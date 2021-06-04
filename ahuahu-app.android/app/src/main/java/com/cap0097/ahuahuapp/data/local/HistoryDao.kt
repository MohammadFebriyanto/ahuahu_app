package com.cap0097.ahuahuapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cap0097.ahuahuapp.utils.Constants

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(history: HistoryEntity)

    @Query("SELECT * FROM ${Constants.TABLE_NAME_HISTORY}")
    fun getAllHistory() : LiveData<List<HistoryEntity>>
}