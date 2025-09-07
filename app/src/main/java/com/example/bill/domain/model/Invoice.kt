package com.example.bill.domain.model

data class Invoice(
    var maHD: String? = null,
    var maKH: String? = null,
    var amount: Double? = null,
    var tenKH: String? = null,
    var totalMoney: String? = null,
    var ngay: String? = null,
)

data class InvoiceItem(
    var cost : String? = null,
    var dongia: String? = null,
    var lai : Long? = null,
    var maHD: String? = null,
    var maSP: String? = null,
    var soluong: String? = null,
    var ten: String? = null,
    var thanhtien: String? = null,
)
