package br.com.dandrade.mercadolivro.controller.output

data class FieldValidationError(
    val field: String,
    val error: String
)
