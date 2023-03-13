package com.rmaprojects.core.data

import com.rmaprojects.core.domain.model.ProductItemDetail
import com.rmaprojects.core.domain.model.ProductItemList

object DummyData {
    fun generateDummyItemList(): List<ProductItemList> {
        return listOf(
            ProductItemList(
                "1",
                "asd",
                "Smartphones",
                "SM-20200"
            ),
            ProductItemList(
                "2",
                "asdf",
                "Smartphones",
                "SM-20202"
            )
        )
    }

    fun generateDummyItemDetail(): ProductItemDetail {
        return ProductItemDetail(
            "SM-20202",
            "2",
            "Smartphones",
            "2020-12-3",
            listOf("asdfsad", "asdasd"),
            "20MP",
            "22MP",
            "200cm",
            "300cm",
            "1.4kg",
            "22mm",
            "N/A",
            "Black",
            "299x300",
            "30hz",
            "300nits",
            "Android 1000",
            "Intel Core i10",
            "300GB",
            "200GB"
        )
    }
}