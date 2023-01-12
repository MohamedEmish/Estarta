package com.example.estarta.di

import com.example.common.Mapper
import com.example.data.mapper.ProductDataDomainMapper
import com.example.data.model.ProductDTO
import com.example.domain.entity.ProductEntity
import com.example.feature.mapper.ProductDomainUiMapper
import com.example.feature.model.ProductUiModel
import com.example.local.mapper.ProductLocalDataMapper
import com.example.local.model.ProductLocalModel
import com.example.remote.mapper.ProductNetworkDataMapper
import com.example.remote.model.ProductNetworkResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Mappers
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    //region Locale Mappers
    @Binds
    abstract fun bindsProductLocalDataMapper(mapper : ProductLocalDataMapper) : Mapper<ProductLocalModel, ProductDTO>
    //endregion

    //region Data Mappers
    @Binds
    abstract fun bindsProductDataDomainMapper(mapper : ProductDataDomainMapper) : Mapper<ProductDTO, ProductEntity>
    //endregion

    //region Presentation Mappers
    @Binds
    abstract fun bindsProductDomainUiMapper(mapper : ProductDomainUiMapper) : Mapper<ProductEntity, ProductUiModel>
    //endregion

    //region Remote Mappers
    @Binds
    abstract fun bindsProductNetworkDataMapper(mapper: ProductNetworkDataMapper): Mapper<ProductNetworkResponse, ProductDTO>
    //endregion

}