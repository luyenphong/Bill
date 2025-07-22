package com.example.bill.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreenContent(onAddInvoiceClick = {
        Log.d("MainScreen", "Nút Thêm hóa đơn đã được nhấn")
    })
}