package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Ram(
    @SerializedName("module")
    val module: String,
    @SerializedName("type")
    val type: String
)