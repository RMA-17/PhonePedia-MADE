package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Software(
    @SerializedName("additional_features")
    val additionalFeatures: String,
    @SerializedName("os")
    val os: String,
    @SerializedName("os_version")
    val osVersion: String
)