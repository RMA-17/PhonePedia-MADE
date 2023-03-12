package com.rmaprojects.core.domain.model

data class ProductItemDetail(
    val productModel: String,
    val productId: String,
    val productCategory: String,
    val productDate: String,
    val productImages: List<String>,
    val cameraBack: String,
    val cameraFront: String,
    val designHeight: String,
    val designWidth: String,
    val designWeight: String,
    val designThickness: String,
    val designIpRating: String,
    val designColor: String,
    val displayRes: String,
    val displayRefreshRate: String,
    val displayPixelDensity: String,
    val specsOS: String,
    val specsProcessor: String,
    val specRAM: String,
    val specStorage: String
)