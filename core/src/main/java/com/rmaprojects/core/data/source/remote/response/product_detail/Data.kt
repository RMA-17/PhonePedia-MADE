package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("language")
    val language: String,
    @SerializedName("type")
    val type: String
)