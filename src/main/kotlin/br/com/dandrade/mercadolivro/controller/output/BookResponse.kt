package br.com.dandrade.mercadolivro.controller.output

import br.com.dandrade.mercadolivro.enums.BookStatus
import java.math.BigDecimal

data class BookResponse(
    var id: Long,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerResponse,
    var status: BookStatus
)
