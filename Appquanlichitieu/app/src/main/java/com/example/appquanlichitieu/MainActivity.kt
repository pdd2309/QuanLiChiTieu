package com.example.appquanlichitieu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appquanlichitieu.databinding.ActivityMainBinding
import com.example.appquanlichitieu.ui.BudgetFragment
import com.example.appquanlichitieu.ui.StatisticsFragment
import com.example.appquanlichitieu.ui.TransactionsFragment
import com.example.appquanlichitieu.viewmodel.TransactionViewModel



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var transactionViewModel: TransactionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_transactions -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,
                            TransactionsFragment())
                        .commit()
                    true
                }
                R.id.nav_statistics -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,
                            StatisticsFragment())
                        .commit()
                    true
                }
                R.id.nav_budget -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BudgetFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}