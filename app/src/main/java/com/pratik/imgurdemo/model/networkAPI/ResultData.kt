package com.pratik.imgurdemo.model.networkAPI

sealed class ResultData<out T> {
    data class Success<out T>(val data: T? = null) : ResultData<T>()
    data class Failed(val message: String? = null) : ResultData<Nothing>()
}
