package br.com.dandrade.mercadolivro.controller

import br.com.dandrade.mercadolivro.controller.input.CustomerRequest
import br.com.dandrade.mercadolivro.controller.input.PutCustomerRequest
import br.com.dandrade.mercadolivro.controller.output.CustomerResponse
import br.com.dandrade.mercadolivro.extension.toCustomer
import br.com.dandrade.mercadolivro.extension.toResponse
import br.com.dandrade.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    val service: CustomerService
) {


    @GetMapping
    fun getAll(
        @RequestParam name: String?,
        @PageableDefault(size = 5, page = 0) pageable: Pageable
    ): Page<CustomerResponse> {
        return service.getAll(name, pageable)
            .map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: CustomerRequest): CustomerResponse {
        return service.create(customer.toCustomer())
            .toResponse()
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Long): CustomerResponse {
        return service.getCustomer(id)
            .toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Long, @RequestBody request: PutCustomerRequest) {
        service.updateCustomer(request.toCustomer(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }


}