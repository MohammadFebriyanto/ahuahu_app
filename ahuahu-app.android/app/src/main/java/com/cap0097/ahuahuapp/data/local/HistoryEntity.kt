package com.cap0097.ahuahuapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aprian1337.sarap.utils.Constants

@Entity(tableName = Constants.TABLE_NAME_HISTORY)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var address: String,
    var recomendation: String,
    var airQuality: String,
    var date: String
)