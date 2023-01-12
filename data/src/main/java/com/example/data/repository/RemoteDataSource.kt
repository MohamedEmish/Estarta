package com.example.data.repository

import com.example.data.model.ProductDTO

/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {
    suspend fun getProductsList(): List<ProductDTO>
}