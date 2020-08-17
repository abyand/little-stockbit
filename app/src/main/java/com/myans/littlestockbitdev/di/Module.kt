package com.myans.littlestockbitdev.di

import com.myans.littlestockbitdev.home.watchlist.WatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        WatchViewModel(get())
    }
}