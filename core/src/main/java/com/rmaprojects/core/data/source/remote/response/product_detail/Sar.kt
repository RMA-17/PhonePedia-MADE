package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Sar(
    @SerializedName("body_(eu)")
    val bodyEu: String,
    @SerializedName("body_(usa)")
    val bodyUsa: String,
    @SerializedName("head_(eu)")
    val headEu: String,
    @SerializedName("head_(usa)")
    val headUsa: String
)