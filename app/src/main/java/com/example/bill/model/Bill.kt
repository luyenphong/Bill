package com.example.bill.model

class Bill {
}

data class InvoiceItem(
    val id: Int,
    val productName: String,
    val quantity: Int,
    val unitPrice: Double
) {
    val totalPrice: Double
        get() = quantity * unitPrice
}