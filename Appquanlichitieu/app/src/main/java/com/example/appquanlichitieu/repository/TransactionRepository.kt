package com.example.appquanlichitieu.repository

import com.example.appquanlichitieu.database.TransactionDao
import com.example.appquanlichitieu.model.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {
    val allTransactions: Flow<List<Transaction>> =
        transactionDao.getAllTransactions()
    suspend fun insert(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }
    suspend fun update(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }
    suspend fun delete(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }
}