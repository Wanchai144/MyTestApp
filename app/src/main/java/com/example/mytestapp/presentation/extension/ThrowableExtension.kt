package com.example.mytestapp.presentation.extension

import com.example.mytestapp.network.NoConnectivityException
import com.google.gson.JsonParser
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun Throwable.toNetworkException(
): NetworkException {

    return when (this) {
        is HttpException -> {
            val error = this.response()?.errorBody()?.string()
            var title = this.message()
            var status = this.message()
            var statusCode = this.code()
            var message = this.message
            try {
                status = JsonParser().parse(error).asJsonObject["status"].asString
                title = JsonParser().parse(error).asJsonObject["title"].asString
                statusCode = JsonParser().parse(error).asJsonObject["statusCode"].asInt
                message = JsonParser().parse(error).asJsonObject["message"].asString
            }catch (e: Throwable) {

            }
            val exceptionType =
                if (statusCode == 401) NetworkExceptionType.TokenInvalid else NetworkExceptionType.HttpException
            NetworkException(
                exceptionType = exceptionType,
                throwable = this,
                status = status,
                title = title,
                statusCode = statusCode,
                message = message
            )
        }
        is NoConnectivityException -> {
//      openNoConnectivityAlertDialog(context, vm)
            NetworkException(
                exceptionType = NetworkExceptionType.NoInternetConnection,
                throwable = this,
                status = "NoInternetConnection",
                title = "NoInternetConnection",
                statusCode = 80001,
                message = "NoInternetConnection"
            )
        }
        is SocketTimeoutException -> {
            NetworkException(
                exceptionType = NetworkExceptionType.TimeoutException,
                throwable = this,
                status = "TimeoutException",
                title = "NoInternetConnection",
                statusCode = 80002,
                message = "TimeoutException"
            )
        }
        else -> {
            NetworkException(
                exceptionType = NetworkExceptionType.Other,
                throwable = this,
                status = "OtherException",
                title = "OtherException",
                statusCode = 80003,
                message = "OtherException"
            )
        }
    }
}