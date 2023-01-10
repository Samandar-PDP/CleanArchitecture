package uz.digital.cleanarchitecture.util

sealed class Response<out T> {
    data class Error(val message: String): Response<Nothing>()
    data class Success<T>(val data: T): Response<T>()
}
