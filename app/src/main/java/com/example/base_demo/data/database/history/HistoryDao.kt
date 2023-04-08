package com.example.base_demo.data.database.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.base_demo.data.model.HistoryEntity
import com.example.base_demo.util.Constants

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHistory(historyEntity: HistoryEntity)

    @Query("SELECT * FROM history_table")
    suspend fun getAllHistories(): MutableList<HistoryEntity>

    @Query("DELETE FROM ${Constants.HISTORY_TABLE}")
    fun deleteAllHistories()
}