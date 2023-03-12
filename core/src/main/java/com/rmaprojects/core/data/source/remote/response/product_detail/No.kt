package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class No(
    @SerializedName("Expansion")
    val expansion: String,
    @SerializedName("FM_Radio")
    val fMRadio: String
)