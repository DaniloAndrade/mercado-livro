package br.com.dandrade.mercadolivro.controller

import br.com.dandrade.mercadolivro.controller.input.BookRequest
import br.com.dandrade.mercadolivro.controller.input.PutBookRequest
import br.com.dandrade.mercadolivro.extension.toBook
import br.com.dandrade.mercadolivro.models.Book
import br.com.dandrade.mercadolivro.search.CustomerSearch
import br.com.dandrade.mercadolivro.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    val customerSearch: CustomerSearch,
    val service: BookService
) {


    @GetMapping
    fun findAll(): List<Book> =
        service.findAll()

    @GetMapping("/active")
    fun findActives(): List<Book> =
        service.findActives()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: BookRequest): Book {
        return service.create(request.toBook { id -> customerSearch.searchById(id) })
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Long): Book {
        return service.findById(id)
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Long, @RequestBody request: PutBookRequest) {
        service.update(request.toBook(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }


}