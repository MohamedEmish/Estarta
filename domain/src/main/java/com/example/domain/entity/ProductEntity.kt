package com.example.domain.entity

data class ProductEntity(
    val createdAt: String,
    val price: String,
    val name: String,
    val uid: String,
    val imageIds: List<String>,
    val imageUrls: List<String>,
    val imageUrlsThumbnails: List<String>,
)