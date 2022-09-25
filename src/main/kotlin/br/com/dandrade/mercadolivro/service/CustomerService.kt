package br.com.dandrade.mercadolivro.service

import br.com.dandrade.mercadolivro.models.Customer
import br.com.dandrade.mercadolivro.repository.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val repository: CustomerRepository,
    val bookDeleter: BookDeleterByCustomer
) {


    fun getAll(name: String?): List<Customer> {
        name?.let { n ->
            return repository.findByNameContaining(n)
        }
        return repository.findAll().toList()
    }


    fun create(customer: Customer): Customer {
        return repository.save(customer)
    }


    fun getCustomer(id: Long): Customer {
        return repository.findById(id).orElseThrow()
    }


    fun updateCustomer(customer: Customer) {

        if (repository.existsById(customer.id!!)) {
            throw Exception()
        }
        repository.save(customer)

    }

    fun delete(id: Long) {
        val customer = repository.findByIdOrNull(id)

        customer?.inativar()
        customer?.also {
            bookDeleter.deleteByCustomer(it.id!!)
            repository.save(it)
        }
    }
}