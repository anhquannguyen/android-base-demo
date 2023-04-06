package com.example.base_demo.data.datastore

import javax.inject.Singleton

@Singleton
interface DataStoreHelper {
    suspend fun saveToken(token: String)

    suspend fun getToken(): String
}