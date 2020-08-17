package com.myans.web_services.remote

import com.myans.web_services.entities.TopTierVolumeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareService {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getUpdatetCrypto(@Query("limit") limit: Int = 50, @Query("tsym") currency: String = "USD"): Response<TopTierVolumeResponse>

}