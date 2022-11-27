package com.diegoalvis.sandbox.domain

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Loading(val progress: Int? = null) : Resource<Nothing>()
    data class Failure(val e: Throwable) : Resource<Nothing>()
}