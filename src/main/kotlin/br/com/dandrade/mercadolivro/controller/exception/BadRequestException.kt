package br.com.dandrade.mercadolivro.controller.exception

class BadRequestException(message: String, val errorCode: String) : Exception(message) {

}
