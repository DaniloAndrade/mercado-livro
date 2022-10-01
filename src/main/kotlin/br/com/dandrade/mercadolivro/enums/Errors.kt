package br.com.dandrade.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML0001("ML-0001", "Unknown error: [%s]"),
    ML0002("ML-0002", "Invalid Request!"),
    ML0101("ML-0101", "Book [%s] not exists!"),
    ML0102("ML-0102", "Cannot update book with status [%s]"),
    ML0201("ML-0201", "Customer [%s] not exists!")
}