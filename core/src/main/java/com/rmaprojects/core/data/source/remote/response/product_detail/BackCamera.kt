package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class BackCamera(
    @SerializedName("aperture_(w)")
    val apertureW: String,
    @SerializedName("equivalent_focal_length")
    val equivalentFocalLength: String,
    @SerializedName("features")
    val features: String,
    @SerializedName("flash")
    val flash: String,
    @SerializedName("focus")
    val focus: String,
    @SerializedName("image_format")
    val imageFormat: String,
    @SerializedName("pixel_size")
    val pixelSize: String,
    @SerializedName("resolution")
    val resolution: String,
    @SerializedName("resolution_(h_x_w)")
    val resolutionHXW: String,
    @SerializedName("sensor")
    val sensor: String,
    @SerializedName("video_format")
    val videoFormat: String,
    @SerializedName("video_resolution")
    val videoResolution: String,
    @SerializedName("zoom")
    val zoom: String
)