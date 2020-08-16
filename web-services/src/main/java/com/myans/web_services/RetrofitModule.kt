package com.myans.web_services

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {

    fun provideGson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    fun provideRetrofit(factory: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(client)
        .build()


    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }
}