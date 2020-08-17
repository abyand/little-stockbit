package com.myans.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.myans.database.Resource
import com.myans.database.Stock
import com.myans.web_services.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers

class WatchRepository constructor(private val remoteDataSource: RemoteDataSource
){

    fun getUpdatedTopTierCrypto(): LiveData<Resource<List<Stock>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            emit(remoteDataSource.getTop24CryptoList())
        }
}