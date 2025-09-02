package com.example.bill.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bill.domain.model.Customer
import com.example.bill.domain.model.Invoice
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*

@Composable
fun InvoiceFormScreen(
    customer: Customer,
    invoice: Invoice?,
    onSaveInvoice: (Invoice) -> Unit,
    onCancel: () -> Unit
) {
    var amountText by remember { mutableStateOf(invoice?.amount?.toString() ?: "") }
    var description by remember { mutableStateOf(invoice?.description ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = amountText,
            onValueChange = { amountText = it.filter { c -> c.isDigit() || c == '.' } },
            label = { Text("Số tiền") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Mô tả") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = {
                val amount = amountText.toDoubleOrNull() ?: 0.0
                if (amount <= 0) return@Button
                val newInvoice = invoice?.copy(amount = amount, description = description, customerId = customer.id)
                    ?: Invoice(amount = amount, description = description, customerId = customer.id)
                onSaveInvoice(newInvoice)
            }) {
                Text("Lưu")
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedButton(onClick = onCancel) {
                Text("Hủy")
            }
        }
    }
}