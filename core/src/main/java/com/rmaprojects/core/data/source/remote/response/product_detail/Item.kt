package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("camera")
    val camera: Camera?,
    @SerializedName("date")
    val date: Date?,
    @SerializedName("design")
    val design: Design?,
    @SerializedName("display")
    val display: Display?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("inside")
    val inside: Inside?,
    @SerializedName("No")
    val no: No?,
    @SerializedName("price")
    val price: Price?,
    @SerializedName("product")
    val product: Product?
)