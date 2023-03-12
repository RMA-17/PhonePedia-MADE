package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Inside(
    @SerializedName("audio")
    val audio: Audio,
    @SerializedName("battery")
    val battery: Battery,
    @SerializedName("cellular")
    val cellular: Cellular,
    @SerializedName("location")
    val location: Location,
    @SerializedName("port")
    val port: Port,
    @SerializedName("processor")
    val processor: Processor,
    @SerializedName("ram")
    val ram: Ram,
    @SerializedName("sar")
    val sar: Sar,
    @SerializedName("software")
    val software: Software,
    @SerializedName("storage")
    val storage: Storage,
    @SerializedName("wireless")
    val wireless: Wireless
)