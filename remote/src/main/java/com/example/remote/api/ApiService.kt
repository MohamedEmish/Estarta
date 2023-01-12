package com.example.remote.api

import com.example.remote.model.ProductNetworkResponse
import com.example.remote.model.ResultWrapper
import retrofit2.http.GET

interface ApiService {
    @GET("default/dynamodb-writer")
    suspend fun getProductList(): ResultWrapper<List<ProductNetworkResponse>>
}