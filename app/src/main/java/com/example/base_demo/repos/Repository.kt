package com.example.base_demo.repos

import com.example.base_demo.data.remote.ApiHeader
import com.example.base_demo.data.remote.ApiServices
import com.example.base_demo.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class Repository @Inject constructor() : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined

    @Inject
    lateinit var apiHeader: ApiHeader

    @Inject
    lateinit var apiServices: ApiServices

}