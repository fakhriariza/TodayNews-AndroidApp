package com.example.todaynews

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class BodyData(
    @SerializedName("userID")
    val userId: String? = ""
): Parcelable
