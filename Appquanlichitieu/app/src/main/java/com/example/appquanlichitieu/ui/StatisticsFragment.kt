package com.example.appquanlichitieu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.appquanlichitieu.database.AppDatabase
import com.example.appquanlichitieu.databinding.FragmentStatisticsBinding
import com.example.appquanlichitieu.model.Transaction
import com.example.appquanlichitieu.model.TransactionType
import com.example.appquanlichitieu.repository.TransactionRepository
import com.example.appquanlichitieu.viewmodel.SharedViewModel
import com.example.appquanlichitieu.viewmodel.TransactionViewModel
import com.example.appquanlichitieu.viewmodel.TransactionViewModelFactory
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class StatisticsFragment : Fragment() {
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    private lateinit var transactionViewModel: TransactionViewModel

    // SharedViewModel để chia sẻ dữ liệu giữa các Fragment
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionDao = AppDatabase.getDatabase(requireContext()).transactionDao()

        // Tạo Repository
        val repository = TransactionRepository(transactionDao)

        // Tạo Factory
        val factory = TransactionViewModelFactory(repository)

        // Khởi tạo ViewModel
        transactionViewModel = ViewModelProvider(this, factory)[TransactionViewModel::class.java]

        // Quan sát danh sách giao dịch và cập nhật giao diện
        transactionViewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            setupPieChart(transactions)
            updateBudgetInSharedViewModel(transactions)
        }
    }

    private fun setupPieChart(transactions: List<Transaction>) {
        val pieChart = binding.pieChartExpenses
        val entries = mutableListOf<PieEntry>()

        // Nhóm chi tiêu theo danh mục
        val categoryExpenses = transactions
            .filter { it.type == TransactionType.EXPENSE }
            .groupBy { it.categoryId }
            .mapValues { it.value.sumOf { trans -> trans.amount } }

        categoryExpenses.forEach { (categoryId, total) ->
            entries.add(PieEntry(total.toFloat(), "Chi Tiêu $categoryId"))
        }

        // Nhóm thu nhập theo danh mục
        val categoryIncome = transactions
            .filter { it.type == TransactionType.INCOME }
            .groupBy { it.categoryId }
            .mapValues { it.value.sumOf { trans -> trans.amount } }

        categoryIncome.forEach { (categoryId, total) ->
            entries.add(PieEntry(total.toFloat(), "Thu Nhập $categoryId"))
        }

        val dataSet = PieDataSet(entries, "")

        // Đặt màu sắc cho dữ liệu
        dataSet.colors = entries.map {
            if (it.label.startsWith("Thu Nhập")) android.graphics.Color.GREEN
            else android.graphics.Color.YELLOW
        }

        val pieData = PieData(dataSet)
        pieChart.data = pieData
        pieChart.description.isEnabled = false
        pieChart.centerText = "Phân Bổ Chi Tiêu và Thu Nhập"
        pieChart.animateY(1000)
        pieChart.invalidate()
    }

    private fun updateBudgetInSharedViewModel(transactions: List<Transaction>) {
        // Tính tổng thu nhập và chi tiêu
        val totalIncome = transactions
            .filter { it.type == TransactionType.INCOME }
            .sumOf { it.amount }

        val totalExpense = transactions
            .filter { it.type == TransactionType.EXPENSE }
            .sumOf { it.amount }

        // Cập nhật vào SharedViewModel
        sharedViewModel.updateIncome(totalIncome)
        sharedViewModel.updateExpense(totalExpense)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
