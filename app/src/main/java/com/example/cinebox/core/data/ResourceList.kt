package com.example.cinebox.core.data

sealed class ResourceList<T>(val data: List<T>? = null, val message: String? = null) {
    class Success<T>(data: List<T>) : ResourceList<T>(data)
    class Loading<T>(data: List<T>? = null) : ResourceList<T>(data)
    class Empty<T>(data: List<T>? = null) : ResourceList<T>(data)
    class Error<T>(message: String, data: List<T>? = null) : ResourceList<T>(data, message)
}
