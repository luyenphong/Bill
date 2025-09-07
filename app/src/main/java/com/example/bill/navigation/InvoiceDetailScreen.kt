package com.example.bill.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bill.domain.model.Invoice
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.bill.domain.model.InvoiceItem
import com.example.bill.presentation.MainViewModel
import com.example.bill.toVietnameseCurrency


@Composable
fun InvoiceDetailScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val invoiceItems = viewModel.invoiceDetails

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Chi tiết hóa đơn",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )

        InvoiceDetailTableWithBorder(invoiceItems = invoiceItems)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text("Quay lại")
        }
    }
}

@Composable
fun InvoiceDetailTableWithBorder(invoiceItems: List<InvoiceItem>) {
    // Khung ngoài bảng
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)  // viền bảng ngoài
    ) {
        // Header với viền dưới
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .height(IntrinsicSize.Min) // để border dọc full chiều cao
        ) {
            TableCellWithBorder("Sản phẩm", weight = 2f, isLastColumn = false, isHeader = true)
            TableCellWithBorder("Số lượng", weight = 1f, isLastColumn = false, isHeader = true)
            TableCellWithBorder("Đơn giá", weight = 1f, isLastColumn = false, isHeader = true, textAlign = TextAlign.Start)
            TableCellWithBorder("Thành tiền", weight = 2f, isLastColumn = true, isHeader = true, textAlign = TextAlign.End)
        }

        // Dữ liệu từng dòng với viền giữa các cột và viền dưới
        invoiceItems.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                TableCellWithBorder(item.ten?:"", weight = 2f, isLastColumn = false)
                TableCellWithBorder(item.soluong?:"", weight = 1f, isLastColumn = false, textAlign = TextAlign.Start)
                TableCellWithBorder(item.soluong?:"", weight = 1f, isLastColumn = false, textAlign = TextAlign.Start)
                val money = (item.cost?:"0").toLong() * (item.soluong?.toLong() ?: 0)
                TableCellWithBorder(money.toString().toVietnameseCurrency(), weight = 2f, isLastColumn = true, textAlign = TextAlign.End)
            }
        }
    }
}

@Composable
fun RowScope.TableCellWithBorder(
    text: String,
    weight: Float,
    isLastColumn: Boolean,
    isHeader: Boolean = false,
    textAlign: TextAlign = TextAlign.Start
) {
    Box(
        modifier = Modifier
            .weight(weight)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            fontWeight = if (isHeader) FontWeight.Bold else FontWeight.Normal,
            textAlign = textAlign,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}