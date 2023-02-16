package com.example.todaynews.network

import com.example.todaynews.HeadlinesData
import retrofit2.Call
import retrofit2.http.*

interface TodayNewsApiService {

    @GET("top-headlines")
    fun getHeadlinesData(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<HeadlinesData?>?

    @GET("everything")
    fun getEverythingData(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String
    ): Call<HeadlinesData?>?

    @GET("everything")
    fun getSearchEverythingData(
        @Query("q") q: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): Call<HeadlinesData?>?
}