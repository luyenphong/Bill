package com.example.bill.domain.model

data class Invoice(
    val id: String = "",
    val customerId: String = "",
    val date: Long = System.currentTimeMillis(),
    val amount: Double = 0.0,
    val description: String = ""
)
