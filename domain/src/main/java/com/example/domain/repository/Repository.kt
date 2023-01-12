package com.example.domain.repository

import com.example.common.Resource
import com.example.domain.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {
    suspend fun getProductsList(): Flow<Resource<List<ProductEntity>>>
}