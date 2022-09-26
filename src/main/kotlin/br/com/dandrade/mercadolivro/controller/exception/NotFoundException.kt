package br.com.dandrade.mercadolivro.controller.exception

class NotFoundException(message: String, val errorCode: String) : Exception(message) {
}