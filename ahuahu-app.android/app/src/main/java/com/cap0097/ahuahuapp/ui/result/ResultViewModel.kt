package com.cap0097.ahuahuapp.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cap0097.ahuahuapp.data.local.HistoryEntity
import com.cap0097.ahuahuapp.domain.model.Result
import com.cap0097.ahuahuapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {

    private lateinit var result: LiveData<Result>

    fun setResult(lat : String, long : String){
        result = repository.getResult(lat,long)
    }

    fun getResult() : LiveData<Result> = result

    fun addHistory(historyEntity: HistoryEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(historyEntity)
        }
    }
}