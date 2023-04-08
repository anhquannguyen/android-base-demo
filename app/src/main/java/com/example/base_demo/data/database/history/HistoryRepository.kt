package com.example.base_demo.data.database.history

import com.example.base_demo.data.model.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyDao: HistoryDao
) {
    suspend fun saveHistory(historyEntity: HistoryEntity) {
        withContext(Dispatchers.IO) {
            historyDao.saveHistory(historyEntity)
        }
    }
}