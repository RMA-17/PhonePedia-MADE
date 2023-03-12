package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Storage(
    @SerializedName("capacity")
    val capacity: String,
    @SerializedName("module")
    val module: String?,
    @SerializedName("type")
    val type: String
)