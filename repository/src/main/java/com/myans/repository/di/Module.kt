package com.myans.repository.di

import com.myans.repository.WatchRepository
import org.koin.dsl.module


val repositoryModule = module {
    single {
        WatchRepository(get())
    }
}