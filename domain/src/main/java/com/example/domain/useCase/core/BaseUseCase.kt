package com.example.domain.useCase.core

import com.example.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Base Use Case class
 */
abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildDetailsRequest(
        params: Params?
    ): Flow<Resource<Model>>

    suspend fun execute(params: Params? = null): Flow<Resource<Model>> {
        return try {
            buildDetailsRequest(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}