package com.rmaprojects.core.data.source.remote.response.product_detail


import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("back_camera")
    val backCamera: BackCamera?,
    @SerializedName("back_camera_ii")
    val backCameraIi: BackCameraIi,
    @SerializedName("back_camera_iii")
    val backCameraIii: BackCameraIii,
    @SerializedName("back_camera_iv")
    val backCameraIv: BackCameraIv,
    @SerializedName("front_camera")
    val frontCamera: FrontCamera?,
    @SerializedName("front_camera_ii")
    val frontCameraIi: FrontCameraIi
)