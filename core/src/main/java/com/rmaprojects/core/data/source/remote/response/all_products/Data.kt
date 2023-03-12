package com.rmaprojects.core.data.source.remote.response.all_products


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("page")
    val page: String,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("type")
    val type: String
)