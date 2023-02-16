package com.example.todaynews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todaynews.network.TodayNewsApi
import com.example.todaynews.network.TodayNewsApiService
import retrofit2.Call
import retrofit2.Response

class MainActivityVM: ViewModel() {
    val headlinesData = MutableLiveData<HeadlinesData>()
    val newsData = MutableLiveData<HeadlinesData>()
    val searchData = MutableLiveData<HeadlinesData>()
    val retrofit = TodayNewsApi.getInstance(TodayNewsApiService::class.java)
    private val apiKey: String = "91436ca5008e422a995eca8ac8923380"


    fun fetchHeadlinesData() {
        val apiCall = retrofit.getHeadlinesData("id", apiKey)
        apiCall?.enqueue(object : retrofit2.Callback<HeadlinesData?> {
            override fun onResponse(
                call: Call<HeadlinesData?>,
                response: Response<HeadlinesData?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        headlinesData.value = it
                    }
                }
            }
            override fun onFailure(call: Call<HeadlinesData?>, t: Throwable) {
            }
        })
    }
    fun fetchEverythingData(domain: String) {
        val apiCall = retrofit.getEverythingData(domain, apiKey)
        apiCall?.enqueue(object : retrofit2.Callback<HeadlinesData?> {
            override fun onResponse(
                call: Call<HeadlinesData?>,
                response: Response<HeadlinesData?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        newsData.value = it
                    }
                }
            }
            override fun onFailure(call: Call<HeadlinesData?>, t: Throwable) {
            }
        })
    }

    fun fetchSearchData(q: String) {
        val apiCall = retrofit.getSearchEverythingData(q, "id", apiKey)
        apiCall?.enqueue(object : retrofit2.Callback<HeadlinesData?> {
            override fun onResponse(
                call: Call<HeadlinesData?>,
                response: Response<HeadlinesData?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        searchData.value = it
                    }
                }
            }
            override fun onFailure(call: Call<HeadlinesData?>, t: Throwable) {
            }
        })
    }
}