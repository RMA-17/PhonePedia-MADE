package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Cellular(
    @SerializedName("carrier")
    val carrier: String?,
    @SerializedName("dual_sim_type")
    val dualSimType: String,
    @SerializedName("generation")
    val generation: String,
    @SerializedName("sim_frequencies")
    val simFrequencies: String,
    @SerializedName("sim_ii_frequencies")
    val simIiFrequencies: String,
    @SerializedName("sim_ii_mobile_data")
    val simIiMobileData: String,
    @SerializedName("sim_ii_module")
    val simIiModule: String,
    @SerializedName("sim_ii_slot")
    val simIiSlot: String,
    @SerializedName("sim_mobile_data")
    val simMobileData: String,
    @SerializedName("sim_slot")
    val simSlot: String,
    @SerializedName("sim_type")
    val simType: String
)