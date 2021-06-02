package com.cap0097.ahuahuapp.ui.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cap0097.ahuahuapp.domain.model.Result
import com.cap0097.ahuahuapp.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val repository: NetworkRepository)  : ViewModel() {

    fun setResult(lat : String, long : String) : LiveData<Result> = repository.getResult(lat,long)

}