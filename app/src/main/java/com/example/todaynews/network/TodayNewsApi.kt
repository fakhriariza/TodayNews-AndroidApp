package com.example.todaynews.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TodayNewsApi {
    const val BASE_URL = "https://newsapi.org/v2/"
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> getInstance(service: Class<T>): T{
        return retrofit.create(service)
    }
}
