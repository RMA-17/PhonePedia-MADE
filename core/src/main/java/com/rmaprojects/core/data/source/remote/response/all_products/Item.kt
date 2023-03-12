package com.rmaprojects.core.data.source.remote.response.all_products


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("image")
    val image: Image,
    @SerializedName("product")
    val product: Product
)