package com.example.appquanlichitieu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appquanlichitieu.model.Category
import com.example.appquanlichitieu.model.Transaction

// AppDatabase.kt
@Database(entities = [Transaction::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "expense_tracker_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}