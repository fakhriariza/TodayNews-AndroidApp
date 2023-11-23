package com.example.todaynews.network

import com.example.todaynews.CountData
import retrofit2.Call
import retrofit2.http.*

interface TodayNewsApiService {

    @GET("count")
    fun getCount(): Call<CountData?>?

    @GET("guest")
    fun getGuest(
        @Query("id") id: String
    ): Call<CountData?>?

    @POST("addGuest")
    fun postGuest(
        @Query("id") id: String
    ): Call<CountData?>?
}