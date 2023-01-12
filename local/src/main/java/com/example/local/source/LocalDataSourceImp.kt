package com.example.local.source

import com.example.common.Mapper
import com.example.data.model.ProductDTO
import com.example.data.repository.LocalDataSource
import com.example.local.database.ProductsDAO
import com.example.local.model.ProductLocalModel
import javax.inject.Inject


/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val productsDAO: ProductsDAO,
    private val productMapper: Mapper<ProductLocalModel, ProductDTO>
) : LocalDataSource {


    override suspend fun addItem(product: ProductDTO): Long {
        val productLocalModel = productMapper.to(product)
        return productsDAO.addProductItem(product = productLocalModel)
    }

    override suspend fun getItemByName(name: String): ProductDTO {
        val productLocalModel = productsDAO.getProductByName(name = name)
        return productMapper.from(productLocalModel)
    }

    override suspend fun getItemById(productId: String): ProductDTO {
        val productLocalModel = productsDAO.getProductById(id = productId)
        return productMapper.from(productLocalModel)
    }

    override suspend fun addItems(products: List<ProductDTO>): List<Long> {
        val productLocalList = productMapper.toList(products)
        return productsDAO.addProductItems(Products = productLocalList)
    }

    override suspend fun getItems(): List<ProductDTO> {
        val productLocalList = productsDAO.getProductsList()
        return productMapper.fromList(productLocalList)
    }

    override suspend fun updateItem(product: ProductDTO): Int {
        val productLocalModel = productMapper.to(product)
        return productsDAO.updateProductItem(Product = productLocalModel)
    }

    override suspend fun deleteItemById(id: Int): Int {
        return productsDAO.deleteProductItemById(id = id)
    }

    override suspend fun deleteItem(product: ProductDTO): Int {
        val productLocalModel = productMapper.to(product)
        return productsDAO.deleteProductItem(product = productLocalModel)
    }

    override suspend fun clearCachedItems(): Int {
        return productsDAO.clearCachedProductItems()
    }
}