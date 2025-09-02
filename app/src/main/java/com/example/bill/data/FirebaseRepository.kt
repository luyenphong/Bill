package com.example.bill.data

import com.example.bill.domain.model.Customer
import com.example.bill.domain.model.Invoice
import com.google.firebase.firestore.FirebaseFirestore


class FirebaseRepository {
    private val db = FirebaseFirestore.getInstance()
    private val customersRef = db.collection("KhachHang")
    private val invoicesRef = db.collection("invoices")

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
        invoicesRef.whereEqualTo("customerId", customerId)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val invoices = snapshot.documents.mapNotNull {
                        it.toObject(Invoice::class.java)?.copy(id = it.id)
                    }
                    onResult(invoices)
                }
            }
    }

    fun addInvoice(invoice: Invoice, onComplete: () -> Unit) {
        invoicesRef.add(invoice).addOnSuccessListener { onComplete() }
    }

    fun updateInvoice(invoice: Invoice, onComplete: () -> Unit) {
        invoicesRef.document(invoice.id).set(invoice).addOnSuccessListener { onComplete() }
    }

    fun deleteInvoice(invoiceId: String, onComplete: () -> Unit) {
        invoicesRef.document(invoiceId).delete().addOnSuccessListener { onComplete() }
    }
}