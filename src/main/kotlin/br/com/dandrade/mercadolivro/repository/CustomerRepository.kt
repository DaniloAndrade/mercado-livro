package br.com.dandrade.mercadolivro.repository

import br.com.dandrade.mercadolivro.models.Customer
import br.com.dandrade.mercadolivro.search.CustomerSearch
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long>, CustomerSearch {

    fun findByNameContaining(name: String): List<Customer>

    override fun searchById(id: Long): Customer {
        return findById(id).orElseThrow()
    }
}