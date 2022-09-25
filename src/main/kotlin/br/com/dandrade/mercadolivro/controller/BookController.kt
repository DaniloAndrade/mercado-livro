package br.com.dandrade.mercadolivro.controller

import br.com.dandrade.mercadolivro.controller.input.BookRequest
import br.com.dandrade.mercadolivro.controller.input.PutBookRequest
import br.com.dandrade.mercadolivro.controller.output.BookResponse
import br.com.dandrade.mercadolivro.extension.toBook
import br.com.dandrade.mercadolivro.extension.toResponse
import br.com.dandrade.mercadolivro.models.Book
import br.com.dandrade.mercadolivro.search.CustomerSearch
import br.com.dandrade.mercadolivro.service.BookService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    val customerSearch: CustomerSearch,
    val service: BookService
) {


    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> =
        service.findAll(pageable).map { it.toResponse() }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> =
        service.findActives(pageable).map { it.toResponse() }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: BookRequest): Book {
        return service.create(request.toBook { id -> customerSearch.searchById(id) })
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Long): BookResponse {
        return service.findById(id).toResponse()
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Long, @RequestBody request: PutBookRequest) {
        service.update(request.toBook(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.cancelar(id)
    }


}