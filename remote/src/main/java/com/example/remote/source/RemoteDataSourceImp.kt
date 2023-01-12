package com.example.remote.source

import com.example.common.Mapper
import com.example.data.model.ProductDTO
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import com.example.remote.model.ProductNetworkResponse
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val productMapper: Mapper<ProductNetworkResponse, ProductDTO>,
) : RemoteDataSource {

    override suspend fun getProductsList(): List<ProductDTO> {
        val networkData = apiService.getProductList()
        val productsList: MutableList<ProductDTO> = mutableListOf()
        networkData.results?.forEach {
            productsList.add(productMapper.from(it))
        }
        return productsList
    }
}