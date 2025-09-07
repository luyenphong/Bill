package com.example.bill.data

import android.util.Log
import com.example.bill.domain.model.Customer
import com.example.bill.domain.model.Invoice
import com.example.bill.domain.model.InvoiceItem
import com.google.firebase.firestore.FirebaseFirestore
import java.util.logging.Logger


class FirebaseRepository {
    private val db = FirebaseFirestore.getInstance()
    private val customersRef = db.collection("KhachHang")
    private val invoicesRef = db.collection("HoaDon")
    private val invoiceDetail = db.collection("hoadon_sanpham")

    // CUSTOMER
    fun getCustomers(onResult: (List<Customer>) -> Unit) {
        customersRef.addSnapshotListener { snapshot, _ ->
            if (snapshot != null) {
                val customers = snapshot.documents.mapNotNull {
                    it.toObject(Customer::class.java)?.copy(id = it.id)
                }.sortedBy { it.ten.lowercase() }
                onResult(customers)
            }
        }
    }

    fun addCustomer(customer: Customer, onComplete: () -> Unit) {
        customersRef.add(customer).addOnSuccessListener { onComplete() }
    }

    fun updateCustomer(customer: Customer, onComplete: () -> Unit) {
        customersRef.document(customer.id).set(customer).addOnSuccessListener { onComplete() }
    }

    fun deleteCustomer(customerId: String, onComplete: () -> Unit) {
        customersRef.document(customerId).delete().addOnSuccessListener { onComplete() }
    }

    // INVOICE
    fun getInvoicesByCustomer(customerId: String, onResult: (List<Invoice>) -> Unit) {
        invoicesRef.whereEqualTo("maKH",customerId).addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val invoices = snapshot.documents.mapNotNull {
                        it.toObject(Invoice::class.java)?.copy(maHD = it.id)
                    }
                    onResult(invoices)

                }
            }
    }

    fun addInvoice(invoice: Invoice, onComplete: () -> Unit) {
        invoicesRef.add(invoice).addOnSuccessListener { onComplete() }
    }

    fun updateInvoice(invoice: Invoice, onComplete: () -> Unit) {
        invoicesRef.document(invoice.maHD?:"").set(invoice).addOnSuccessListener { onComplete() }
    }

    fun deleteInvoice(invoiceId: String, onComplete: () -> Unit) {
//        invoicesRef.document(invoiceId).delete().addOnSuccessListener { onComplete() }
    }

    fun getInvoiceDetails(invoiceId: String, onResult: (List<InvoiceItem>) -> Unit) {
        invoiceDetail
            .whereEqualTo("maHD", invoiceId)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val items = snapshot.documents.mapNotNull {
                        it.toObject(InvoiceItem::class.java)
                    }
                    onResult(items)
                }
            }
    }
}