package com.rmaprojects.core.data.source.remote.response.all_products


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("back")
    val back: String?,
    @SerializedName("front")
    val front: String
)