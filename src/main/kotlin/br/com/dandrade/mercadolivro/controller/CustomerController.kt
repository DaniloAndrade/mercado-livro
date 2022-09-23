package br.com.dandrade.mercadolivro.controller

import br.com.dandrade.mercadolivro.controller.input.CustomerRequest
import br.com.dandrade.mercadolivro.controller.input.PutCustomerRequest
import br.com.dandrade.mercadolivro.extension.toCustomer
import br.com.dandrade.mercadolivro.models.Customer
import br.com.dandrade.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    val service: CustomerService
) {


    @GetMapping
    fun getAll(@RequestParam name: String?): List<Customer> {
        return service.getAll(name)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: CustomerRequest): Customer {
        return service.create(customer.toCustomer())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Long): Customer {
        return service.getCustomer(id)
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