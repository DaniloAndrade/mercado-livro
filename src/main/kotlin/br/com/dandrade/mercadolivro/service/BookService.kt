package br.com.dandrade.mercadolivro.service

import br.com.dandrade.mercadolivro.controller.exception.NotFoundException
import br.com.dandrade.mercadolivro.enums.BookStatus
import br.com.dandrade.mercadolivro.enums.Errors
import br.com.dandrade.mercadolivro.models.Book
import br.com.dandrade.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val repository: BookRepository
) : BookDeleterByCustomer {


    fun findAll(pageable: Pageable): Page<Book> {
        return repository.findAll(pageable)
    }


    fun create(book: Book): Book {
        return repository.save(book)
    }

    fun findActives(pageable: Pageable): Page<Book> {
        return repository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Long): Book {
        return repository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML0101.message.format(id), Errors.ML0101.code) }
    }

    fun cancelar(id: Long) {
        val book = findById(id)
        book.cancelar()
        repository.save(book)
    }

    fun update(book: Book) {
        val maybeBook = findById(book.id!!)
        maybeBook.also {
            it.name = book.name
            it.price = book.price
        }
        repository.save(maybeBook)
    }

    override fun deleteByCustomer(id: Long) {
        val books = repository.findByCustomerId(id)
        val bookToDelete = books.filter { it.status != BookStatus.VENDIDO }
            .map { it.delete(); it }
        repository.saveAll(bookToDelete)
    }


}