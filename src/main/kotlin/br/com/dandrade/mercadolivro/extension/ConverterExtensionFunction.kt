package br.com.dandrade.mercadolivro.extension

import br.com.dandrade.mercadolivro.controller.input.BookRequest
import br.com.dandrade.mercadolivro.controller.input.CustomerRequest
import br.com.dandrade.mercadolivro.controller.input.PutBookRequest
import br.com.dandrade.mercadolivro.controller.input.PutCustomerRequest
import br.com.dandrade.mercadolivro.controller.output.BookResponse
import br.com.dandrade.mercadolivro.controller.output.CustomerResponse
import br.com.dandrade.mercadolivro.enums.BookStatus
import br.com.dandrade.mercadolivro.models.Book
import br.com.dandrade.mercadolivro.models.Customer


fun CustomerRequest.toCustomer(): Customer {
    return Customer(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomer(id: Long): Customer {
    return Customer(id = id, name = this.name, email = this.email)
}


fun BookRequest.toBook(find: (Long) -> Customer): Book {
    val customer = find(this.customerId)
    return Book(name = this.name, price = this.price, status = BookStatus.ATIVO, customer = customer)
}

fun PutBookRequest.toBook(id: Long): Book {
    return Book(id = id, name = this.name!!, price = this.price!!)
}


fun Customer.toResponse(): CustomerResponse =
    CustomerResponse(this.id!!, this.name, this.email, this.status)

fun Book.toResponse(): BookResponse = BookResponse(
    id = this.id!!,
    name = this.name,
    price = this.price,
    customer = this.customer?.toResponse()!!,
    status = this.status!!
)