package com.myans.web_services.entities

import com.google.gson.annotations.SerializedName

data class TopTierVolumeResponse(
    @SerializedName("Message") val message: String,
    @SerializedName("Type") val status: Int,
    @SerializedName("Data") val data: List<BitCoinInfoResponse>
)

data class BitCoinInfoResponse(
    @SerializedName("CoinInfo") val coinInfo: CoinInfo,
    @SerializedName("RAW") val rawInfo: RawInfo

)

data class CoinInfo(
    @SerializedName("Id") val id: String,
    @SerializedName("Name") val name: String,
    @SerializedName("FullName") val fullName: String
)
data class RawInfo(
    @SerializedName("USD") val usdInfo: USDInfo
)

data class USDInfo(
    @SerializedName("PRICE") val currentPrice: Double,
    @SerializedName("OPENDAY") val openPrice: Double
)