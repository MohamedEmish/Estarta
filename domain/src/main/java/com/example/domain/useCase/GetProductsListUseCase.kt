package com.example.domain.useCase

import com.example.common.Resource
import com.example.domain.entity.ProductEntity
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import com.example.domain.useCase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<List<ProductEntity>, Map<String, String>>() {

    override suspend fun buildDetailsRequest(params: Map<String, String>?): Flow<Resource<List<ProductEntity>>> =
        repository.getProductsList().flowOn(dispatcher)
}