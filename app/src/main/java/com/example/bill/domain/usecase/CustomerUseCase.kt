package com.example.bill.domain.usecase

import com.example.bill.data.FirebaseRepository
import com.example.bill.domain.model.Customer

class CustomerUseCase(private val repo: FirebaseRepository) {
    fun getCustomers(onResult: (List<Customer>) -> Unit) = repo.getCustomers(onResult)
    fun addCustomer(customer: Customer, onComplete: () -> Unit) = repo.addCustomer(customer, onComplete)
    fun updateCustomer(customer: Customer, onComplete: () -> Unit) = repo.updateCustomer(customer, onComplete)
    fun deleteCustomer(customerId: String, onComplete: () -> Unit) = repo.deleteCustomer(customerId, onComplete)
}