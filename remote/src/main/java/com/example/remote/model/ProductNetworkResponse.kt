package com.example.remote.model

data class ProductNetworkResponse(
    val created_at: String? = null,
    val price: String? = null,
    val name: String? = null,
    val uid: String? = null,
    val image_ids: List<String>? = mutableListOf(),
    val image_urls: List<String>? = mutableListOf(),
    val image_urls_thumbnails: List<String>? = mutableListOf()
)