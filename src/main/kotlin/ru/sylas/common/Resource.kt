package ru.sylas.common

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    sealed class Success<T>(data: T?) : Resource<T>(data = data)
    sealed class Error<T>(message: String?):Resource<T>(message = message)
}
