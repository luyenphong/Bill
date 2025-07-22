package com.example.bill.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bill.header.HeaderCard
import com.example.bill.loading.FullScreenLoading

@Composable
fun MainScreen(navController: NavController) {
    MainScreenContent(
        onAddInvoiceClick = {
            Log.d("MainScreen", "Nút Thêm hóa đơn đã được nhấn")
            navController.navigate("add_invoice_screen")
        }
    )
}

@Composable
fun MainScreenContent(onAddInvoiceClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top
        ) {
            HeaderCard()

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Nút "Thêm hóa đơn" căn dưới cùng
        AddInvoiceButton(
            onClick = {
                onAddInvoiceClick.invoke()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Đặt nút ở dưới cùng
                .padding(16.dp)
        )

        // Hiển thị trạng thái loading nếu cần
        FullScreenLoading(isLoading = false)
    }
}

@Composable
fun AddInvoiceButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = {
            // In log để kiểm tra sự kiện click
            Log.d("ButtonClick", "Nút Thêm hóa đơn đã được nhấn")
            onClick.invoke()
        },
        modifier = modifier
            .fillMaxWidth() // Chiếm hết chiều rộng
            .height(56.dp) // Chiều cao của nút
            .padding(5.dp), // Padding cho nút
        shape = RoundedCornerShape(12.dp), // Bo tròn góc với bán kính 12dp
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4B2B)) // Màu nền cho nút
    ) {
        Text(
            text = "Thêm hóa đơn",
            color = Color.White, // Màu chữ trắng
            style = TextStyle( // Định nghĩa kiểu chữ tùy chỉnh
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp, // Kích thước chữ
                letterSpacing = 0.5.sp // Khoảng cách giữa các chữ
            ) // Style chữ chuẩn cho button
        )
    }
}