package com.example.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val createdAt: String,
    val price: String,
    val name: String,
    val uid: String,
    val imageIds: String,
    val imageUrls: String,
    val imageUrlsThumbnails: String,
)
