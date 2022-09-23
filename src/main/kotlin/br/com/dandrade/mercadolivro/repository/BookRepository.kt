package br.com.dandrade.mercadolivro.repository

import br.com.dandrade.mercadolivro.enums.BookStatus
import br.com.dandrade.mercadolivro.models.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long> {

    fun findByStatus(status: BookStatus): List<Book>

    fun findByCustomerId(customerId: Long): List<Book>
}