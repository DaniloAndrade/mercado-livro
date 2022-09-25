package br.com.dandrade.mercadolivro.repository

import br.com.dandrade.mercadolivro.enums.BookStatus
import br.com.dandrade.mercadolivro.models.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

    fun findByStatus(status: BookStatus, pageable: Pageable): Page<Book>

    fun findByCustomerId(customerId: Long): List<Book>
}