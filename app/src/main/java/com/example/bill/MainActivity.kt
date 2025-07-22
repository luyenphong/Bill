package com.example.bill

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bill.invoice.AddInvoiceScreen
import com.example.bill.invoice.AddInvoiceScreenHost
import com.example.bill.main.MainScreen
import com.example.bill.ui.theme.BillTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BillTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding -> // đây là paddingValues
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.padding(innerPadding) // <== Thêm dòng này
                    ) {
                        composable("main") {
                            MainScreen(navController = navController)
                        }
                        composable("add_invoice_screen") {
                            AddInvoiceScreenHost(navController)
                        }
                    }
                }
            }
        }
    }
}