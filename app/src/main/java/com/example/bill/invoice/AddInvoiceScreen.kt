package com.example.bill.invoice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale


@Composable
fun AddInvoiceScreen(
    onSave: (productName: String, quantity: Int, unitPrice: Double, originalPrice: Double) -> Unit,
    onCancel: () -> Unit
) {
    var productName by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("") }
    var unitPriceText by remember { mutableStateOf("") }
    var originalPriceText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Thêm Hóa Đơn", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Tên sản phẩm
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Tên sản phẩm") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Số lượng (chỉ nhập số)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = quantityText,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        quantityText = input
                    }
                },
                label = { Text("Số lượng") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Đơn giá (số thập phân)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = unitPriceText,
                onValueChange = { input ->
                    if (input.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        unitPriceText = input
                    }
                },
                label = { Text("Đơn giá") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Giá gốc (số thập phân)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = originalPriceText,
                onValueChange = { input ->
                    if (input.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        originalPriceText = input
                    }
                },
                label = { Text("Giá gốc") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val quantity = quantityText.toIntOrNull() ?: 0
                    val unitPrice = unitPriceText.toDoubleOrNull() ?: 0.0
                    val originalPrice = originalPriceText.toDoubleOrNull() ?: 0.0
                    onSave(productName, quantity, unitPrice, originalPrice)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Lưu")
            }
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Hủy")
            }
        }
    }
}

fun String.formatMoney(currencySymbol: String = "₫"): String {
    val formatter = NumberFormat.getNumberInstance(Locale("vi", "VN"))
    val formatted = formatter.format(this.toLongOrNull())
    return "$formatted $currencySymbol"
}
