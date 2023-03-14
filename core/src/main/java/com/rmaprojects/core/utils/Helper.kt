package com.rmaprojects.core.utils

import com.rmaprojects.core.data.source.local.entity.FavoriteEntity
import com.rmaprojects.core.domain.model.Favorite
import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.model.ProductItemList
import com.rmaprojects.core.data.source.remote.response.all_products.Item as DataProductList
import com.rmaprojects.core.data.source.remote.response.product_detail.Item as DataProductDetail

fun ProductItemDetail.mapIntoFavorite(): FavoriteEntity {
    return FavoriteEntity(
        productId = this.productId,
        productCategory = this.productCategory,
        productImage = this.productImages.first(),
        productName = this.productModel
    )
}

fun Favorite.asFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        this.productId,
        this.productCategory,
        this.productImage,
        this.productName
    )
}

fun FavoriteEntity?.asFavorite(): Favorite {
    return if (this != null) Favorite(
        this.productId,
        this.productCategory,
        this.productImage,
        this.productName
    ) else Favorite(
        "",
        null,
        null,
        null
    )
}


fun DataProductList.mapIntoEntity(): ProductItemList {
    return ProductItemList(
        productId = this.product.id,
        productModel = this.product.model,
        image = this.image?.front ?: "",
        category = this.product.category
    )
}

fun DataProductDetail.mapIntoEntity(): ProductItemDetail {
    return ProductItemDetail(
        productModel = this.product?.model ?: "N/A",
        productImages = listOf(this.image?.front ?: "", this.image?.back ?: ""),
        productId = this.product?.id ?: "",
        productCategory = this.product?.category ?: "",
        productDate = this.date?.released ?: "N/A",
        cameraBack = this.camera?.backCamera?.resolution ?: "N/A",
        cameraFront = this.camera?.frontCamera?.resolution ?: "N/A",
        designHeight = this.design?.body?.height ?: "N/A",
        designColor = this.design?.body?.color ?: "N/A",
        designWidth = this.design?.body?.width ?: "N/A",
        designWeight = this.design?.body?.weight ?: "N/A",
        designThickness = this.design?.body?.thickness ?: "N/A",
        designIpRating = this.design?.body?.ipRating ?: "N/A",
        displayRes = this.display?.resolutionHXW ?: "N/A",
        displayRefreshRate = this.display?.refreshRate ?: "N/A",
        displayPixelDensity = this.display?.pixelDensity ?: "N/A",
        specsOS = this.inside?.software?.os ?: "N/A",
        specsProcessor = this.inside?.processor?.cpu ?: "N/A",
        specRAM = this.inside?.ram?.type ?: "N/A",
        specStorage = this.inside?.storage?.capacity ?: "N/A",
    )
}