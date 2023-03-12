package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("color")
    val color: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("ip_rating")
    val ipRating: String,
    @SerializedName("thickness")
    val thickness: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("width")
    val width: String
)