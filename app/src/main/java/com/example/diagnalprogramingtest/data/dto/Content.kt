package com.example.diagnalprogramingtest.data.dto

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "poster-image")
    val posterImage: String
)