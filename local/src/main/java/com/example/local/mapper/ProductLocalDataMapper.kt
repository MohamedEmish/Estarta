package com.example.local.mapper

import com.example.common.Mapper
import com.example.common.getListOfStrings
import com.example.data.model.ProductDTO
import com.example.local.model.ProductLocalModel
import javax.inject.Inject

class ProductLocalDataMapper @Inject constructor() : Mapper<ProductLocalModel, ProductDTO> {

    override fun from(i: ProductLocalModel?): ProductDTO {
        return ProductDTO(
            createdAt = i?.createdAt ?: "",
            price = i?.price ?: "",
            name = i?.name ?: "",
            uid = i?.uid ?: "",
            imageIds = i?.imageIds?.getListOfStrings() ?: mutableListOf(),
            imageUrls = i?.imageUrls?.getListOfStrings() ?: mutableListOf(),
            imageUrlsThumbnails = i?.imageUrlsThumbnails?.getListOfStrings() ?: mutableListOf()
        )
    }

    override fun to(o: ProductDTO?): ProductLocalModel {
        return ProductLocalModel(
            createdAt = o?.createdAt ?: "",
            price = o?.price ?: "",
            name = o?.name ?: "",
            uid = o?.uid ?: "",
            imageIds = o?.imageIds?.joinToString(",") ?: "",
            imageUrls = o?.imageUrls?.joinToString(",") ?: "",
            imageUrlsThumbnails = o?.imageUrlsThumbnails?.joinToString(",") ?: ""
        )
    }
}