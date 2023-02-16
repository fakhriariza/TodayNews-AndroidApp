package com.example.todaynews

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class HeadlinesData(
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("articles")
    val data: List<Articles> = arrayListOf()
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    @Keep
    data class Articles(
        @SerializedName("source")
        val source: Source? = null,
        @SerializedName("author")
        val author: String? = "",
        @SerializedName("title")
        val title: String? = "",
        @SerializedName("url")
        val url: String? = "",
        @SerializedName("urlToImage")
        val urlToImage: String? = "",
        @SerializedName("publishedAt")
        val publishedAt: String? = "",
        @SerializedName("content")
        val content: String? = "",
        @SerializedName("description")
        val description: String? = "",
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        @Keep
        data class Source(
            @SerializedName("id")
            val id: String = "",
            @SerializedName("name")
            val name: String = "",
        ) : Parcelable
    }
}
