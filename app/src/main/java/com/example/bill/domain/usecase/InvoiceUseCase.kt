package com.example.bill.domain.usecase

import com.example.bill.data.FirebaseRepository
import com.example.bill.domain.model.Invoice
import com.example.bill.domain.model.InvoiceItem

class InvoiceUseCase(private val repo: FirebaseRepository) {
    fun getInvoicesByCustomer(customerId: String, onResult: (List<Invoice>) -> Unit) =
        repo.getInvoicesByCustomer(customerId, onResult)

    fun addInvoice(invoice: Invoice, onComplete: () -> Unit) = repo.addInvoice(invoice, onComplete)
    fun updateInvoice(invoice: Invoice, onComplete: () -> Unit) =
        repo.updateInvoice(invoice, onComplete)

    fun deleteInvoice(invoiceId: String, onComplete: () -> Unit) =
        repo.deleteInvoice(invoiceId, onComplete)

    fun getInvoiceDetails(invoiceId: String, onResult: (List<InvoiceItem>) -> Unit) =
        repo.getInvoiceDetails(invoiceId, onResult)
}