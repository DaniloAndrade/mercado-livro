package br.com.dandrade.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML0001("ML-0001", "Unknown error: [%s]"),
    ML0101("ML-0101", "Book [%s] not exists!"),
    ML0201("ML-0201", "Customer [%s] not exists!")
}