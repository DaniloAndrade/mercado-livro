package br.com.dandrade.mercadolivro.controller.output

data class ErrorResponse(
    val httpCode: Int,
    val message: String,
    val internalErrorCode: String,
    val errors: List<FieldValidationError>?
)
