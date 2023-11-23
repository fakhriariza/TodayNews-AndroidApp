package com.example.todaynews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todaynews.network.TodayNewsApi
import com.example.todaynews.network.TodayNewsApiService
import retrofit2.Call
import retrofit2.Response

class MainActivityVM: ViewModel() {
    val countData = MutableLiveData<CountData>()
    val guestData = MutableLiveData<CountData>()
    val postData = MutableLiveData<Boolean>()
    val retrofit = TodayNewsApi.getInstance(TodayNewsApiService::class.java)
    private val apiKey: String = "91436ca5008e422a995eca8ac8923380"


    fun fetchCountData() {
        val apiCall = retrofit.getCount()
        apiCall?.enqueue(object : retrofit2.Callback<CountData?> {
            override fun onResponse(
                call: Call<CountData?>,
                response: Response<CountData?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        countData.value = it
                    }
                }
            }
            override fun onFailure(call: Call<CountData?>, t: Throwable) {
            }
        })
    }
    fun fetchGuestData(id: String) {
        val apiCall = retrofit.getGuest(id)
        apiCall?.enqueue(object : retrofit2.Callback<CountData?> {
            override fun onResponse(
                call: Call<CountData?>,
                response: Response<CountData?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        guestData.value = it
                    }
                }
            }
            override fun onFailure(call: Call<CountData?>, t: Throwable) {
            }
        })
    }

    fun submitGuestList(id: String) {
        val apiCall = retrofit.postGuest(id)
        apiCall?.enqueue(object : retrofit2.Callback<CountData?> {
            override fun onResponse(
                call: Call<CountData?>,
                response: Response<CountData?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        postData.value = true
                    }
                }
            }
            override fun onFailure(call: Call<CountData?>, t: Throwable) {
                postData.value = false
            }
        })
    }
}