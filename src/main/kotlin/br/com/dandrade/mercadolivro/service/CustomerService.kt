package br.com.dandrade.mercadolivro.service

import br.com.dandrade.mercadolivro.controller.exception.NotFoundException
import br.com.dandrade.mercadolivro.enums.Errors
import br.com.dandrade.mercadolivro.models.Customer
import br.com.dandrade.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val repository: CustomerRepository,
    val bookDeleter: BookDeleterByCustomer
) {


    fun getAll(name: String?, pageable: Pageable): Page<Customer> {
        name?.let { n ->
            return repository.findByNameContaining(n, pageable)
        }
        return repository.findAll(pageable)
    }


    fun create(customer: Customer): Customer {
        return repository.save(customer)
    }


    fun getCustomer(id: Long): Customer {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML0201.message.format(id), Errors.ML0201.code) }
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