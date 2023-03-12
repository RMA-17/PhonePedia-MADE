package com.rmaprojects.core.data.source.remote.response.categories


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total")
    val total: Int
)