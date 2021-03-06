package com.cap0097.ahuahuapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cap0097.ahuahuapp.utils.Constants

@Entity(tableName = Constants.TABLE_NAME_HISTORY)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var address: String,
    var recomendation: String,
    var airQuality: String,
    var date: String
)