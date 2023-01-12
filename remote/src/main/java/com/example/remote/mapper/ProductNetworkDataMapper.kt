package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.ProductDTO
import com.example.remote.model.ProductNetworkResponse
import javax.inject.Inject

class ProductNetworkDataMapper @Inject constructor() :
    Mapper<ProductNetworkResponse, ProductDTO> {

    override fun from(i: ProductNetworkResponse?): ProductDTO {
        return ProductDTO(
            createdAt = i?.created_at ?: "",
            price = i?.price ?: "",
            name = i?.name ?: "",
            uid = i?.uid ?: "",
            imageIds = i?.image_ids ?: mutableListOf(),
            imageUrls = i?.image_urls ?: mutableListOf(),
            imageUrlsThumbnails = i?.image_urls_thumbnails ?: mutableListOf()
        )
    }

    override fun to(o: ProductDTO?): ProductNetworkResponse {
        return ProductNetworkResponse()
    }
}