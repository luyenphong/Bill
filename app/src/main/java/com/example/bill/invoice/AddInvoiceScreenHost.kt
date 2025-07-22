package com.example.bill.invoice

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AddInvoiceScreenHost(navController: NavController) {
    AddInvoiceScreen(
        onSave = { productName, quantity, unitPrice, originalPrice ->
            // Xử lý lưu hóa đơn, ví dụ gọi ViewModel hoặc điều hướng
            println("Lưu hóa đơn: $productName, qty=$quantity, unitPrice=$unitPrice, originalPrice=$originalPrice")
            navController.popBackStack() // Quay về màn hình trước
        },
        onCancel = {
            navController.popBackStack()
        }
    )
}