package com.example.bill.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bill.domain.model.Customer
import com.example.bill.domain.model.Invoice
import com.example.bill.domain.usecase.CustomerUseCase
import com.example.bill.domain.usecase.InvoiceUseCase

class MainViewModel(
    private val customerUseCase: CustomerUseCase,
    private val invoiceUseCase: InvoiceUseCase
) : ViewModel() {

    var selectedCustomer by mutableStateOf<Customer?>(null)
        private set

    var invoices by mutableStateOf(listOf<Invoice>())
        private set

    var customers by mutableStateOf<List<Customer>>(emptyList())
        private set

    init {
        loadCustomers()
    }

    fun loadCustomers() {
        customerUseCase.getCustomers { list ->
            customers = list
        }
    }

    fun selectCustomer(customer: Customer) {
        selectedCustomer = customer
        loadInvoices(customer.id)
    }

    fun clearSelectedCustomer() {
        selectedCustomer = null
        invoices = emptyList()
    }

    fun addCustomer(customer: Customer, onComplete: () -> Unit) {
        customerUseCase.addCustomer(customer, onComplete)
    }

    fun updateCustomer(customer: Customer, onComplete: () -> Unit) {
        customerUseCase.updateCustomer(customer, onComplete)
    }

    fun deleteCustomer(customerId: String, onComplete: () -> Unit) {
        customerUseCase.deleteCustomer(customerId, onComplete)
    }

    fun loadInvoices(customerId: String) {
        invoiceUseCase.getInvoicesByCustomer(customerId) { list ->
            invoices = list
        }
    }

    fun addInvoice(invoice: Invoice, onComplete: () -> Unit) {
        invoiceUseCase.addInvoice(invoice, onComplete)
    }

    fun updateInvoice(invoice: Invoice, onComplete: () -> Unit) {
        invoiceUseCase.updateInvoice(invoice, onComplete)
    }

    fun deleteInvoice(invoiceId: String, onComplete: () -> Unit) {
        invoiceUseCase.deleteInvoice(invoiceId, onComplete)
    }
}