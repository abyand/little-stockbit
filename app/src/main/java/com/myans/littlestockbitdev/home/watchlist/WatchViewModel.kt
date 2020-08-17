package com.myans.littlestockbitdev.home.watchlist

import androidx.lifecycle.ViewModel
import com.myans.repository.WatchRepository

class WatchViewModel constructor(private val repository: WatchRepository): ViewModel() {
    fun getTop24CrptoUpdate() = repository.getUpdatedTopTierCrypto()
}