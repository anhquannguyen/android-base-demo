package com.example.base_demo.ui.base

import androidx.lifecycle.ViewModel
import com.example.base_demo.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel(), DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    protected open val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->

        }

    protected fun Result<Any>.catchException() {
        if (this.isFailure) {
            coroutineExceptionHandler.handleException(main, exceptionOrNull()!!)
        }
    }

}