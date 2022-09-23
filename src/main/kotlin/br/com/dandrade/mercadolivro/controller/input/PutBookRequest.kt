package br.com.dandrade.mercadolivro.controller.input

import java.math.BigDecimal

data class PutBookRequest(
    var name: String?,
    var price: BigDecimal?
)
