package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Processor(
    @SerializedName("cpu")
    val cpu: String,
    @SerializedName("cpu_clock_speed")
    val cpuClockSpeed: String,
    @SerializedName("gpu")
    val gpu: String
)