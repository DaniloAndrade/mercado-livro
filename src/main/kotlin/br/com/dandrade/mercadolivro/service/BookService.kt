package br.com.dandrade.mercadolivro.service

import br.com.dandrade.mercadolivro.enums.BookStatus
import br.com.dandrade.mercadolivro.models.Book
import br.com.dandrade.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val repository: BookRepository
) : BookDeleterByCustomer {


    fun findAll(): List<Book> {
        return repository.findAll().toList()
    }


    fun create(book: Book): Book {
        return repository.save(book)
    }

    fun findActives(): List<Book> {
        return repository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Long): Book {
        return repository.findById(id).orElseThrow()
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