package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Display(
    @SerializedName("bezel_width")
    val bezelWidth: String,
    @SerializedName("color_depth")
    val colorDepth: String,
    @SerializedName("diagonal")
    val diagonal: String,
    @SerializedName("dynamic_range")
    val dynamicRange: String,
    @SerializedName("glass")
    val glass: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("illumination")
    val illumination: String,
    @SerializedName("lcd_mode")
    val lcdMode: String,
    @SerializedName("notch")
    val notch: String,
    @SerializedName("number_of_colors")
    val numberOfColors: String,
    @SerializedName("pixel_density")
    val pixelDensity: String,
    @SerializedName("pixel_size")
    val pixelSize: String,
    @SerializedName("refresh_rate")
    val refreshRate: String,
    @SerializedName("resolution_(h_x_w)")
    val resolutionHXW: String,
    @SerializedName("screen_to_body_ratio")
    val screenToBodyRatio: String,
    @SerializedName("subpixels")
    val subpixels: String,
    @SerializedName("touch_screen_type")
    val touchScreenType: String,
    @SerializedName("touchpoints")
    val touchpoints: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("width")
    val width: String
)