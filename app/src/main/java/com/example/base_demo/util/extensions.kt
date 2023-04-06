package com.example.base_demo.util

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

//fun <T> Fragment.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
//    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collect(collect)
//        }
//    }
//}

fun <T> Fragment.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

fun View.setOnSafeClickListener(safeTime: Long = 300, clickListener: (View?) -> Unit) {
    setOnClickListener(SafeOnClickListener.newInstance(safeTime) {
        clickListener(it)
    })
}

sealed class DataState<T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    class Failure(val exception: Exception) : DataState<Nothing>()
    object Undefined : DataState<Nothing>()
}