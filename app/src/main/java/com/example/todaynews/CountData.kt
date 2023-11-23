package com.example.todaynews

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class CountData(
    @SerializedName("total_guest")
    val totalGuest: String? = "",
    @SerializedName("data")
    val data: Data? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    @Keep
    data class Data(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("Nama_Undangan")
        val namaUndangan: String? = null,
        @SerializedName("Status")
        val status: String? = null,
        @SerializedName("ID")
        val idid: String? = null,
        @SerializedName("Tautan")
        val tautan: String? = null,
        @SerializedName("createdAt")
        val createdAt: String? = null,
        @SerializedName("updatedAt")
        val updatedAt: String? = null
    ) : Parcelable
}
