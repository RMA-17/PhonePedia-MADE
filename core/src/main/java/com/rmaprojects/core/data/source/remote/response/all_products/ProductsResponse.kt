package com.rmaprojects.core.data.source.remote.response.all_products


import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)