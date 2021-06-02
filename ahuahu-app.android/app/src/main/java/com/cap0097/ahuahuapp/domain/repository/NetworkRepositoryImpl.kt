package com.cap0097.ahuahuapp.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cap0097.ahuahuapp.data.NetworkDataSource
import com.cap0097.ahuahuapp.data.Resource
import com.cap0097.ahuahuapp.domain.model.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val networkDataSource : NetworkDataSource
) : NetworkRepository {
    override fun getResult(lat: String, long: String): LiveData<Result> {
        val result = MutableLiveData<Result>()
        GlobalScope.launch {
            networkDataSource.getResult(lat, long, object: NetworkDataSource.LoadResultCallback{
                override fun onResultReceived(results: Resource<Result>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }
}