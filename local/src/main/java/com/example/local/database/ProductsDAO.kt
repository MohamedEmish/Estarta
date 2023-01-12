package com.example.local.database

import androidx.room.*
import com.example.local.model.ProductLocalModel

@Dao
interface ProductsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductItem(product: ProductLocalModel): Long

    @Query("SELECT * FROM products WHERE name = :name")
    suspend fun getProductByName(name: String): ProductLocalModel

    @Query("SELECT * FROM products WHERE uid = :id")
    suspend fun getProductById(id: String): ProductLocalModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductItems(Products: List<ProductLocalModel>): List<Long>

    @Query("SELECT * FROM products")
    suspend fun getProductsList(): List<ProductLocalModel>

    @Update
    suspend fun updateProductItem(Product: ProductLocalModel): Int

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProductItemById(id: Int): Int

    @Delete
    suspend fun deleteProductItem(product: ProductLocalModel): Int

    @Query("DELETE FROM products")
    suspend fun clearCachedProductItems(): Int
}