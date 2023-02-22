package com.fukuhara.douglas.lib.common.arch

sealed class Result<out T, out M> {
    data class Failure<out T>(val throwable: T) : Result<T, Nothing>()
    data class Success<out M>(val model: M) : Result<Nothing, M>()

    inline fun <R> fold(failure: (T) -> R, success: (M) -> R): R = when (this) {
        is Failure -> failure(throwable)
        is Success -> success(model)
    }
}

fun <T> T.failure() = Result.Failure(this)
fun <M> M.success() = Result.Success(this)
