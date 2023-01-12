package com.example.data.repository

import com.example.data.model.ProductDTO


/**
 * Methods of Local Data Source
 */
interface LocalDataSource {

    suspend fun addItem(product: ProductDTO): Long

    suspend fun getItemByName(name: String): ProductDTO

    suspend fun getItemById(productId: String): ProductDTO

    suspend fun addItems(products: List<ProductDTO>): List<Long>

    suspend fun getItems(): List<ProductDTO>

    suspend fun updateItem(product: ProductDTO): Int

    suspend fun deleteItemById(id: Int): Int

    suspend fun deleteItem(product: ProductDTO): Int

    suspend fun clearCachedItems(): Int
}