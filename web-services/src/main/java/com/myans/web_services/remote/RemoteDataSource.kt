package com.myans.web_services.remote

import com.myans.database.entities.Crypto
import com.myans.database.entities.Resource
import com.myans.web_services.entities.TopTierVolumeResponse
import retrofit2.Response

class RemoteDataSource constructor(private val cryptoCompareService: CryptoCompareService) {

    suspend fun getTop24CryptoList() = getResult {
        cryptoCompareService.getUpdatedCrypto()
    }

    protected suspend fun getResult(call: suspend () -> Response<TopTierVolumeResponse>): Resource<List<Crypto>> {
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
            return Resource.error("Failed to connect")
        } catch (e: Exception) {
            return Resource.error(e.message ?: e.toString())
        }
    }

    private fun bridgeToListOfStock(respons: TopTierVolumeResponse): List<Crypto> {
        return respons.data.map {
            Crypto(
                name = it.coinInfo.name,
                companyName = it.coinInfo.fullName,
                currentValue = it.rawInfo.usdInfo.currentPrice,
                openValue = it.rawInfo.usdInfo.openPrice
            )
        }
    }
}