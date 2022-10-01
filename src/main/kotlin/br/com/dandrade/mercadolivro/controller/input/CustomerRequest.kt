package br.com.dandrade.mercadolivro.controller.input

import br.com.dandrade.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CustomerRequest(
    @field:NotEmpty
    var name: String,
    @field:Email
    @EmailAvailable
    var email: String
)
