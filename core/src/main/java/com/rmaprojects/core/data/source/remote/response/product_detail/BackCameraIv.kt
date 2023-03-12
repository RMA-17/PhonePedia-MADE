package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class BackCameraIv(
    @SerializedName("sensor")
    val sensor: String
)