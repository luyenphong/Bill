package com.example.bill

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bill.data.FirebaseRepository
import com.example.bill.domain.usecase.CustomerUseCase
import com.example.bill.domain.usecase.InvoiceUseCase
import com.example.bill.presentation.AppContent
import com.example.bill.presentation.MainViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repo = FirebaseRepository()
        val customerUseCase = CustomerUseCase(repo)
        val invoiceUseCase = InvoiceUseCase(repo)
        val viewModel = MainViewModel(customerUseCase,invoiceUseCase)

        setContent {
            AppContent(viewModel)
        }
    }
}