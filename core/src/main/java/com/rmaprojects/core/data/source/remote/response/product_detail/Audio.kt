package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Audio(
    @SerializedName("av_out")
    val avOut: String,
    @SerializedName("av_resolution")
    val avResolution: String,
    @SerializedName("channel")
    val channel: String,
    @SerializedName("hearing_aid_compatibility")
    val hearingAidCompatibility: String,
    @SerializedName("microphone")
    val microphone: String,
    @SerializedName("microphone_input")
    val microphoneInput: String,
    @SerializedName("output")
    val output: String
)