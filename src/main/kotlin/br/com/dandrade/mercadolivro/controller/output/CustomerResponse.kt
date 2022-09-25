package br.com.dandrade.mercadolivro.controller.output

import br.com.dandrade.mercadolivro.enums.CustomerStatus

data class CustomerResponse(
    var id: Long,
    var name: String,
    var email: String,
    var status: CustomerStatus
)
