package br.com.dandrade.mercadolivro.controller.exception

import br.com.dandrade.mercadolivro.controller.output.ErrorResponse
import br.com.dandrade.mercadolivro.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(ex: Exception, request: WebRequest): ErrorResponse {
        return ErrorResponse(
            httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = Errors.ML0001.message.format(ex.message ?: ""),
            internalErrorCode = Errors.ML0001.code,
            errors = null
        )
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ErrorResponse {
        return ErrorResponse(
            httpCode = HttpStatus.NOT_FOUND.value(),
            message = ex.message ?: "Erro desconhecido",
            internalErrorCode = ex.errorCode,
            errors = null
        )
    }
}