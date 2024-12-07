package com.example.appquanlichitieu.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.appquanlichitieu.model.Transaction
import kotlinx.coroutines.flow.Flow

// TransactionDao.kt
@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>
    @Insert
    suspend fun insertTransaction(transaction: Transaction)
    @Update
    suspend fun updateTransaction(transaction: Transaction)
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}
