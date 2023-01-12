package com.example.remote.model

data class ResultWrapper<T>(
    val pagination: Pagination? = null,
    val results: T? = null
)