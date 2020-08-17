package com.myans.web_services.remote

import com.myans.database.Resource
import com.myans.database.Stock
import com.myans.web_services.entities.TopTierVolumeResponse
import retrofit2.Response

class RemoteDataSource constructor(private val cryptoCompareService: CryptoCompareService) {

    suspend fun getTop24CryptoList() = getResult {
        cryptoCompareService.getUpdatetCrypto()
    }

    protected suspend fun getResult(call: suspend () -> Response<TopTierVolumeResponse>): Resource<List<Stock>> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    if (it.status != 100)
                        return Resource.error(it.message)
                    else
                        return Resource.success(bridgeToListOfStock(it))
                }
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return Resource.error(e.message ?: e.toString())
        }
    }

    private fun bridgeToListOfStock(respons: TopTierVolumeResponse): List<Stock> {
        return respons.data.map {
            Stock(
                name = it.coinInfo.name,
                companyName = it.coinInfo.fullName,
                currentValue = it.rawInfo.usdInfo.currentPrice,
                openValue = it.rawInfo.usdInfo.openPrice
            )
        }
    }
}