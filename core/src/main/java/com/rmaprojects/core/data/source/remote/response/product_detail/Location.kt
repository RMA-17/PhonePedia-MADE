package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("additional_features")
    val additionalFeatures: String
)