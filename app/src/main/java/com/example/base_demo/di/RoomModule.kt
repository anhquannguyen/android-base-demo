package com.example.base_demo.di

import android.app.Application
import androidx.room.Room
import com.example.base_demo.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, AppDatabase::class.java, "code_scan_database").build()

    @Provides
    @Singleton
    fun provideHistoryDao(database: AppDatabase) = database.historyDao()
}