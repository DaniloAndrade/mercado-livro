package br.com.dandrade.mercadolivro.repository

import br.com.dandrade.mercadolivro.models.Customer
import br.com.dandrade.mercadolivro.search.CustomerSearch
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long>, CustomerSearch {

    fun findByNameContaining(name: String, pageable: Pageable): Page<Customer>

    override fun searchById(id: Long): Customer {
        return findById(id).orElseThrow()
    }
}