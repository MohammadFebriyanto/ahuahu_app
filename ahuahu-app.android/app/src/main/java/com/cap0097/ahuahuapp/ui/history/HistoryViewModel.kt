package com.cap0097.ahuahuapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cap0097.ahuahuapp.data.local.HistoryEntity
import com.cap0097.ahuahuapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: Repository) : ViewModel(){
    private lateinit var history : LiveData<List<HistoryEntity>>

    fun setAllHistory(){
        history =  repository.getAllHistory()
    }

    fun getAllHistory() : LiveData<List<HistoryEntity>> = history
}