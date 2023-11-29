package com.example.todaynews

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class FirebaseData(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("status")
    val status: String? = null
) : Parcelable
