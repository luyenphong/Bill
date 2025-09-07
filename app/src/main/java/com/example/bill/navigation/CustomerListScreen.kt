package com.example.bill.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bill.domain.model.Customer
import com.example.bill.presentation.MainViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedButton
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CustomerListScreen(
    viewModel: MainViewModel,
    onAddCustomer: () -> Unit,
    onEditCustomer: (Customer) -> Unit,
    onDeleteCustomer: (String) -> Unit,
    onCustomerSelected: (Customer) -> Unit
) {

    val customers = viewModel.customers

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tiêu đề màn hình tự đặt, thay vì TopAppBar
        Text(
            text = "Danh sách khách hàng",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = onAddCustomer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm khách hàng")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(customers) { customer ->
                CustomerItem(
                    customer = customer,
                    onEdit = { onEditCustomer(customer) },
                    onDelete = { onDeleteCustomer(customer.id) },
                    onClick = { onCustomerSelected(customer) }
                )
            }
        }
    }
}

@Composable
fun CustomerItem(
    customer: Customer,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Tên: ${customer.ten} - ${customer.sodienthoai}", style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A73E8)
            )
        )
        Text(text = "Địa chỉ: ${customer.diachi}")

        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Button(onClick = onEdit) {
                Text("Sửa")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(onClick = onDelete) {
                Text("Xóa")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(onClick = onClick) {
                Text("Xem hóa đơn")
            }
        }
    }
}