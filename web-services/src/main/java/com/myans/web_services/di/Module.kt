package com.myans.web_services.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.myans.web_services.remote.CryptoCompareService
import com.myans.web_services.remote.RemoteDataSource
import com.tinder.scarlet.Scarlet
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val serviceModule = module {

    fun provideGson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    fun provideRetrofit(factory: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://min-api.cryptocompare.com/")
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(client)
        .build()


    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }

    fun provideWatchService(retrofit: Retrofit) = retrofit.create(CryptoCompareService::class.java)

    single { provideWatchService(get())}
    single { RemoteDataSource(get())}

}