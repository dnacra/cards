package com.hatfat.fab.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubFabRetrofitFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
) {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/the-fab-cube/flesh-and-blood-cards/main/json/english/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}