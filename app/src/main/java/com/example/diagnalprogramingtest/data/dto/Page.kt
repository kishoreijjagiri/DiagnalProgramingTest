package com.example.diagnalprogramingtest.data.dto

import com.google.gson.annotations.SerializedName
data class Page(
    @SerializedName(value = "content-items")
    val contentItems: ContentItems,
    @SerializedName(value = "page-num")
    val pageNum: String,
    @SerializedName(value = "page-size")
    val pageSize: String,
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "total-content-items")
    val totalContentItems: String
)