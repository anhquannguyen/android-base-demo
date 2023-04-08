package com.example.base_demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.base_demo.data.database.history.HistoryDao
import com.example.base_demo.data.model.HistoryEntity

@Database(
    entities = [
        HistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
