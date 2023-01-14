package uz.digital.cleanarchitecture.util

sealed class Response<out T> {
    object Loading: Response<Nothing>()
    data class Error(val message: String): Response<Nothing>()
    data class Success<T>(val data: T): Response<T>()
}
