package com.example.feature.mapper

import com.example.common.Mapper
import com.example.domain.entity.ProductEntity
import com.example.feature.model.ProductUiModel
import javax.inject.Inject

class ProductDomainUiMapper @Inject constructor() : Mapper<ProductEntity, ProductUiModel> {
    override fun from(i: ProductEntity?): ProductUiModel {
        return ProductUiModel(
            createdAt = i?.createdAt ?: "",
            price = i?.price ?: "",
            name = i?.name ?: "",
            uid = i?.uid ?: "",
            imageIds = i?.imageIds ?: mutableListOf(),
            imageUrls = i?.imageUrls ?: mutableListOf(),
            imageUrlsThumbnails = i?.imageUrlsThumbnails ?: mutableListOf()
        )
    }

    override fun to(o: ProductUiModel?): ProductEntity {
        return ProductEntity(
            createdAt = o?.createdAt ?: "",
            price = o?.price ?: "",
            name = o?.name ?: "",
            uid = o?.uid ?: "",
            imageIds = o?.imageIds ?: mutableListOf(),
            imageUrls = o?.imageUrls ?: mutableListOf(),
            imageUrlsThumbnails = o?.imageUrlsThumbnails ?: mutableListOf()
        )
    }
}