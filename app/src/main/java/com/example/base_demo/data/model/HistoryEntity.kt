package com.example.base_demo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.base_demo.util.Constants

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val value: String?,
    var type: String?,
)