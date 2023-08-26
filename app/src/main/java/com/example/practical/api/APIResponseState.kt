package com.example.practical.api

sealed class APIResponseState<out T>{
    data class Success<out T>(
        val data: T?
    ) : APIResponseState<T>()

    data class Error(val errorMessage: String) : APIResponseState<Nothing>()
}
