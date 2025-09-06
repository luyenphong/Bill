package com.example.bill

import java.text.NumberFormat
import java.util.Locale

fun String.toVietnameseCurrency(): String {
    return try {
        val number = this.toDouble()
        NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(number)
    } catch (e: NumberFormatException) {
        "Invalid amount"
    }
}