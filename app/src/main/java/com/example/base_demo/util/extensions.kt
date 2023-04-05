package com.example.base_demo.util

import android.util.Log
import com.example.base_demo.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

fun showLog(tag: String = BuildConfig.APPLICATION_ID, msg: String) {
    if (!BuildConfig.DEBUG) {
        return
    }
    Log.d(tag, msg)
}

suspend fun <T> Flow<T>.getResult(): T = this.flowOn(Dispatchers.IO)
    .catch { Result.failure<Exception>(it) }
    .onEach { Result.success(it) }
    .single()

sealed class DataState<T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    class Failure(val exception: Exception) : DataState<Nothing>()
    object Undefined : DataState<Nothing>()
}