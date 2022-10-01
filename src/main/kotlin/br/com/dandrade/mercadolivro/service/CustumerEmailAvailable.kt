package br.com.dandrade.mercadolivro.service

interface CustumerEmailAvailable {

    fun emailAvailable(email: String): Boolean
}