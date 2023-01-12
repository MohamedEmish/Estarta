package com.example.data.model

data class ProductDTO(
    val createdAt: String,
    val price: String,
    val name: String,
    val uid: String,
    val imageIds: List<String>,
    val imageUrls: List<String>,
    val imageUrlsThumbnails: List<String>,
)
