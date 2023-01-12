package com.example.data.repository

import com.example.common.Mapper
import com.example.common.Resource
import com.example.data.model.ProductDTO
import com.example.domain.entity.ProductEntity
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val productMapper: Mapper<ProductDTO, ProductEntity>,
) : Repository {

    override suspend fun getProductsList(): Flow<Resource<List<ProductEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getProductsList()
                // Emit data
                val resultList: MutableList<ProductEntity> = mutableListOf()
                data.forEach {
                    resultList.add(productMapper.from(it))
                }
                emit(Resource.Success(resultList))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getItems()
                    // Emit data
                    val resultList: MutableList<ProductEntity> = mutableListOf()
                    local.forEach {
                        resultList.add(productMapper.from(it))
                    }
                    emit(Resource.Success(resultList))
                } catch (ex1: Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }
}