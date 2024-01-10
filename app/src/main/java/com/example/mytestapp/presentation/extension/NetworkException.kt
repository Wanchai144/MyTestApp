package com.example.mytestapp.presentation.extension

data class NetworkException(
    val exceptionType: NetworkExceptionType,
    val throwable: Throwable,
    val statusCode: Int?,
    val status: String?,
    val title:String?,
    val message: String?
)

enum class NetworkExceptionType {
    NoInternetConnection, TimeoutException, HttpException, TokenInvalid, Other
}
