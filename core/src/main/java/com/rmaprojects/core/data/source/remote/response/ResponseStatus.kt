package com.rmaprojects.core.data.source.remote.response

sealed class ResponseStatus<out T> {
    data class Success<T>(val data: T): ResponseStatus<T>()
    data class Error(val message: String): ResponseStatus<Nothing>()
    object Loading: ResponseStatus<Nothing>()
}