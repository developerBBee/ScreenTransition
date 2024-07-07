package com.example.screentransition.myMode.data.common

sealed interface MyResult<T> {
    data class Success<T>(val data: T) : MyResult<T>
    data class Error<T>(val exception: Throwable) : MyResult<T>
}