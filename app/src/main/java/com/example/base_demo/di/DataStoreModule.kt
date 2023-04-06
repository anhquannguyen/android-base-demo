package com.example.base_demo.di

import com.example.base_demo.data.datastore.DataStoreHelper
import com.example.base_demo.data.datastore.DataStoreManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {
    @Binds
    abstract fun provideDataStoreHelper(dataStoreManager: DataStoreManager): DataStoreHelper
}
