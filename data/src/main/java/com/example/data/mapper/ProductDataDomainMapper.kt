package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.ProductDTO
import com.example.domain.entity.ProductEntity
import javax.inject.Inject

class ProductDataDomainMapper @Inject constructor() :
    Mapper<ProductDTO, ProductEntity> {

    override fun from(i: ProductDTO?): ProductEntity {
        return ProductEntity(
            createdAt = i?.createdAt ?: "",
            price = i?.price ?: "",
            name = i?.name ?: "",
            uid = i?.uid ?: "",
            imageIds = i?.imageIds ?: mutableListOf(),
            imageUrls = i?.imageUrls ?: mutableListOf(),
            imageUrlsThumbnails = i?.imageUrlsThumbnails ?: mutableListOf()
        )
    }

    override fun to(o: ProductEntity?): ProductDTO {
        return ProductDTO(
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