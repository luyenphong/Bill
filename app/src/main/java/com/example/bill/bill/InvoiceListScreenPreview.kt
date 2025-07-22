package com.example.bill.bill

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bill.model.InvoiceItem

@Preview(showBackground = true)
@Composable
fun InvoiceListScreenPreview() {
    val sampleInvoices = listOf(
        InvoiceItem(1, "Bánh mì", 2, 15000.0),
        InvoiceItem(2, "Trà sữa", 1, 30000.0),
        InvoiceItem(3, "Phở", 3, 40000.0)
    )
    InvoiceListScreenCustom(
        invoices = sampleInvoices,
        customerName = "Luyện Phong",
        createdDate = "07/02/1997",
        onBackClick = {},
        onDeleteAllClick = {},
        onAddMoreClick = {

        })
}

