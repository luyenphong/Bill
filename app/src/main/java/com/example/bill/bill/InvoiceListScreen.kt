package com.example.bill.bill

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bill.R
import com.example.bill.model.InvoiceItem
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight


@Composable
fun InvoiceListScreenCustom(
    invoices: List<InvoiceItem>,
    customerName: String,
    createdDate: String,
    onBackClick: () -> Unit,
    onAddMoreClick: () -> Unit,
    onDeleteAllClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            // Hàng trên cùng: Nút back và QR
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }

                Column(
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = customerName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = createdDate,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                QrImage()
            }

            // Thông tin khách hàng
            Spacer(modifier = Modifier.height(8.dp))


            // Danh sách hoá đơn
            Spacer(modifier = Modifier.height(16.dp))

            val totalAmount = invoices.sumOf { it.quantity * it.unitPrice }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f) // Chiếm hết phần còn lại
                    .fillMaxWidth()
            ) {
                items(invoices) { invoice ->
                    InvoiceCard(invoice)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TotalAmountBox(totalAmount = totalAmount)

            // Nút Xoá và Thêm
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onDeleteAllClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Xoá hóa đơn", color = Color.White)
                }

                Button(
                    onClick = onAddMoreClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Thêm vào hóa đơn", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun QrImage() {
    Image(
        painter = painterResource(id = R.drawable.your_qr_image), // đổi your_qr_image thành tên ảnh trong drawable
        contentDescription = "QR Code",
        modifier = Modifier.size(60.dp) // kích thước tuỳ chỉnh
    )
}

@Composable
fun InvoiceCard(invoice: InvoiceItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Sản phẩm: ${invoice.productName}", fontWeight = FontWeight.Bold)
            Text(text = "Số lượng: ${invoice.quantity}")
            Text(text = "Đơn giá: ${invoice.unitPrice}đ")
            Text(
                text = "Thành tiền: ${invoice.totalPrice}đ",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun TotalAmountBox(totalAmount: Double) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Tổng số tiền: ${totalAmount}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.Black
        )
    }
}