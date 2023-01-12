package com.example.feature.model

data class ProductUiModel(
    val createdAt: String,
    val price: String,
    val name: String,
    val uid: String,
    val imageIds: List<String>,
    val imageUrls: List<String>,
    val imageUrlsThumbnails: List<String>,
)