package com.example.appquanlichitieu.viewmodel

import androidx.lifecycle.*
import com.example.appquanlichitieu.model.Transaction
import com.example.appquanlichitieu.repository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionRepository) :
    ViewModel() {
    val allTransactions: LiveData<List<Transaction>> =
        repository.allTransactions.asLiveData()
    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }
    fun update(transaction: Transaction) = viewModelScope.launch {
        repository.update(transaction)
    }
    fun delete(transaction: Transaction) = viewModelScope.launch {
        repository.delete(transaction)
    }
}