package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class BackCameraIii(
    @SerializedName("aperture_(w)")
    val apertureW: String,
    @SerializedName("equivalent_focal_length")
    val equivalentFocalLength: String,
    @SerializedName("features")
    val features: String,
    @SerializedName("focus")
    val focus: String,
    @SerializedName("resolution")
    val resolution: String,
    @SerializedName("sensor")
    val sensor: String
)