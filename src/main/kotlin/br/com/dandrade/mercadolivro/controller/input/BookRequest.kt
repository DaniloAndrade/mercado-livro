package br.com.dandrade.mercadolivro.controller.input

import java.math.BigDecimal

data class BookRequest(
    var name: String,
    var price: BigDecimal,
    var customerId: Long
)
