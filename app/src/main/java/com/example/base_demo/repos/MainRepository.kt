package com.example.base_demo.repos

import com.example.base_demo.util.getResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor() : Repository() {

    suspend fun getUser() = flow {
        emit(apiServices.getUsers())
    }.getResult()

    suspend fun getNumber() = flow {
        listOf(5, 3, 0, 2).forEach {
            emit(60 / it)
        }
    }.getResult()

}