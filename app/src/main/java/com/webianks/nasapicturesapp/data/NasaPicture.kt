package com.webianks.nasapicturesapp.data

import com.google.gson.annotations.SerializedName

data class NasaPicture(
    @SerializedName("copyright") val copyright: String,
    @SerializedName("date") val date: String,
    @SerializedName("explanation") val explanation: String,
    @SerializedName("hdUrl") val hdUrl: String,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("service_version") val service_version: String,
    @SerializedName("tile") val title: String,
    @SerializedName("url") val url: String,
)
