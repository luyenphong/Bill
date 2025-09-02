package com.example.bill.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bill.domain.model.Customer
import com.example.bill.domain.model.Invoice
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*


@Composable
fun InvoiceListScreen(
    customer: Customer,
    invoices: List<Invoice>,
    onAddInvoice: () -> Unit,
    onEditInvoice: (Invoice) -> Unit,
    onDeleteInvoice: (String) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tiêu đề tự làm
        Text(
            text = "Hóa đơn của: ${customer.ten}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick = onAddInvoice, modifier = Modifier.fillMaxWidth()) {
            Text("Thêm hóa đơn")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (invoices.isEmpty()) {
            Text("Chưa có hóa đơn nào.")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(invoices) { invoice ->
                    InvoiceItem(
                        invoice = invoice,
                        onEdit = { onEditInvoice(invoice) },
                        onDelete = { onDeleteInvoice(invoice.id) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Quay lại danh sách khách hàng")
        }
    }
}

@Composable
fun InvoiceItem(
    invoice: Invoice,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Số tiền: ${invoice.amount}")
        Text("Mô tả: ${invoice.description}")

        Row(modifier = Modifier.padding(top = 4.dp)) {
            Button(onClick = onEdit) {
                Text("Sửa")
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(onClick = onDelete) {
                Text("Xóa")
            }
        }
    }
}