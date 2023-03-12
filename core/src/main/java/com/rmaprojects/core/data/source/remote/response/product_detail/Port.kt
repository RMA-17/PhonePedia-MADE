package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Port(
    @SerializedName("usb_features")
    val usbFeatures: String,
    @SerializedName("usb_type")
    val usbType: String,
    @SerializedName("usb_version")
    val usbVersion: String
)