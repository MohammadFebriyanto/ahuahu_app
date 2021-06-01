package com.cap0097.ahuahuapp.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cap0097.ahuahuapp.data.remote.response.ResultResponse

class ResultViewModel : ViewModel() {
    private val result: MutableLiveData<ResultResponse>()

    fun setResult(latLong: String) {

    }

    fun getResult() : LiveData<ResultResponse> = result
}