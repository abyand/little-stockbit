package com.myans.littlestockbitdev

import android.app.Application
import com.myans.littlestockbitdev.di.viewModelModule
import com.myans.repository.di.repositoryModule
import com.myans.web_services.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StockbitApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@StockbitApplication)
            modules(listOf(viewModelModule, repositoryModule, serviceModule))
        }
    }
}