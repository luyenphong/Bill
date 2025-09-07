package com.example.bill.presentation

import androidx.compose.runtime.Composable
import com.example.bill.domain.model.Customer
import com.example.bill.domain.model.Invoice
import com.example.bill.navigation.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import com.example.bill.navigation.CustomerFormScreen
import com.example.bill.navigation.CustomerListScreen
import com.example.bill.navigation.InvoiceDetailScreen
import com.example.bill.navigation.InvoiceFormScreen
import com.example.bill.navigation.InvoiceListScreen

@Composable
fun AppContent(viewModel: MainViewModel) {
    var currentScreen by remember { mutableStateOf(Screen.CUSTOMER_LIST) }
    var editingCustomer by remember { mutableStateOf<Customer?>(null) }
    var editingInvoice by remember { mutableStateOf<Invoice?>(null) }

    when (currentScreen) {
        Screen.CUSTOMER_LIST -> CustomerListScreen(
            viewModel = viewModel,
            onAddCustomer = {
                editingCustomer = null
                currentScreen = Screen.CUSTOMER_FORM
            },
            onEditCustomer = {
                editingCustomer = it
                currentScreen = Screen.CUSTOMER_FORM
            },
            onDeleteCustomer = { customerId ->
//                viewModel.deleteCustomer(customerId) {}
            },
            onCustomerSelected = {
                viewModel.selectCustomer(it)
                currentScreen = Screen.INVOICE_LIST
            }
        )

        Screen.CUSTOMER_FORM -> CustomerFormScreen(
            customer = editingCustomer,
            onSaveCustomer = { customer ->
                if (customer.id.isEmpty()) {
                    viewModel.addCustomer(customer) { currentScreen = Screen.CUSTOMER_LIST }
                } else {
                    viewModel.updateCustomer(customer) { currentScreen = Screen.CUSTOMER_LIST }
                }
            },
            onCancel = { currentScreen = Screen.CUSTOMER_LIST }
        )

        Screen.INVOICE_LIST -> {
            val customer = viewModel.selectedCustomer
            if (customer != null) {
                InvoiceListScreen (
                    viewModel = viewModel,
                    customer = customer,
                    onAddInvoice = {
                        editingInvoice = null
                        currentScreen = Screen.INVOICE_FORM
                    },
                    onEditInvoice = {
                        editingInvoice = it
                        currentScreen = Screen.INVOICE_FORM
                    },
                    onDeleteInvoice = { invoiceId ->
                        viewModel.deleteInvoice(invoiceId) {}
                    },
                    onViewDetail = {  invoice ->
                        viewModel.selectInvoice(invoice)
                        viewModel.loadInvoiceDetails(invoice.maHD ?: "")
                        currentScreen = Screen.INVOICE_DETAIL
                    },
                    onBack = {
                        viewModel.clearSelectedCustomer()
                        currentScreen = Screen.CUSTOMER_LIST
                    }
                )
            }
        }

        Screen.INVOICE_FORM -> {
            val customer = viewModel.selectedCustomer
            val invoice = editingInvoice
            if (customer != null) {
                InvoiceFormScreen(
                    customer = customer,
                    invoice = invoice,
                    onSaveInvoice = { invoiceToSave ->
                        if ((invoiceToSave.maHD?:"").isEmpty()) {
                            viewModel.addInvoice(invoiceToSave) { currentScreen = Screen.INVOICE_LIST }
                        } else {
                            viewModel.updateInvoice(invoiceToSave) { currentScreen = Screen.INVOICE_LIST }
                        }
                    },
                    onCancel = { currentScreen = Screen.INVOICE_LIST }
                )
            }
        }

        Screen.INVOICE_DETAIL -> {
            val invoice = viewModel.selectedInvoice
            if (invoice != null) {
                InvoiceDetailScreen(
                    viewModel = viewModel,
                    onBack = {
                        viewModel.clearSelectedInvoice()
                        currentScreen = Screen.INVOICE_LIST
                    }
                )
            }
        }
    }
}