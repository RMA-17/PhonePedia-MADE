package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Battery(
    @SerializedName("capacity")
    val capacity: String,
    @SerializedName("cell_i")
    val cellI: String,
    @SerializedName("charging_power")
    val chargingPower: String,
    @SerializedName("power_ic")
    val powerIc: String,
    @SerializedName("style")
    val style: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("wireless_charging")
    val wirelessCharging: String,
    @SerializedName("wireless_charging_power")
    val wirelessChargingPower: String
)