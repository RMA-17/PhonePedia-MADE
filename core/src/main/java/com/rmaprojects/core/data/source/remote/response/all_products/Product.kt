package com.rmaprojects.core.data.source.remote.response.all_products


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("brand")
    val brand: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("model")
    val model: String,
    @SerializedName("version")
    val version: String?
)