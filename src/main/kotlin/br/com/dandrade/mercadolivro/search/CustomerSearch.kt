package br.com.dandrade.mercadolivro.search

import br.com.dandrade.mercadolivro.models.Customer

interface CustomerSearch {

    fun searchById(id: Long): Customer
}