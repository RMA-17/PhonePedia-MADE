package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("announced")
    val announced: String,
    @SerializedName("released")
    val released: String
)