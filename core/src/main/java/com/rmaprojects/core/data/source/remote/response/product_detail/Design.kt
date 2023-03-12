package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Design(
    @SerializedName("body")
    val body: Body
)