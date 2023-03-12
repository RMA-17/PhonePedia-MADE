package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Wireless(
    @SerializedName("bluetooth_module")
    val bluetoothModule: String,
    @SerializedName("bluetooth_profiles")
    val bluetoothProfiles: String,
    @SerializedName("bluetooth_version")
    val bluetoothVersion: String,
    @SerializedName("experiences")
    val experiences: String,
    @SerializedName("wifi")
    val wifi: String,
    @SerializedName("wifi_features")
    val wifiFeatures: String,
    @SerializedName("wlan_module")
    val wlanModule: String
)