package com.example.todaynews.network

import com.example.todaynews.BodyData
import com.example.todaynews.CountData
import com.example.todaynews.CountDataList
import com.example.todaynews.DashboardData
import retrofit2.Call
import retrofit2.http.*

interface TodayNewsApiService {

    @GET("guests-attends")
    fun getCount(): Call<DashboardData?>?

    @POST("attend")
    fun getGuest(
        @Body id: BodyData
    ): Call<CountData?>?

    @FormUrlEncoded
    @POST("attend")
    fun postGuest(
        @Query("userID") id: String
    ): Call<CountData?>?
}