package com.example.todaynews

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class DashboardData(
    @SerializedName("statusCode")
    val statusCode: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("data")
    val data: Data? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    @Keep
    data class Data(
        @SerializedName("totalCount")
        val totalCount: String? = null,
        @SerializedName("userAttend")
        val userAttend: ArrayList<UserAttend>,
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class UserAttend(
            @SerializedName("status")
            val status: String? = null,
            @SerializedName("count")
            val count: Int? = null,
        ) : Parcelable
    }
}
