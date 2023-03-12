package com.rmaprojects.core.data.source.remote.response.categories


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("count")
    val count: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("name")
    val name: String
)