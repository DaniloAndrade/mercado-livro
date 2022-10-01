package br.com.dandrade.mercadolivro.controller.exception

import br.com.dandrade.mercadolivro.controller.output.ErrorResponse
import br.com.dandrade.mercadolivro.controller.output.FieldValidationError
import br.com.dandrade.mercadolivro.enums.Errors
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    final val LOG = LoggerFactory.getLogger(ControllerAdvice::class.java)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleException(ex: Exception, request: WebRequest): ErrorResponse {
        LOG.error("Unexpected error", ex)
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

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ErrorResponse {
        return ErrorResponse(
            httpCode = HttpStatus.BAD_REQUEST.value(),
            message = ex.message ?: "Erro desconhecido",
            internalErrorCode = ex.errorCode,
            errors = null
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    fun handleBadRequestException(ex: MethodArgumentNotValidException, request: WebRequest): ErrorResponse {
        return ErrorResponse(
            httpCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            message = Errors.ML0002.message,
            internalErrorCode = Errors.ML0002.code,
            errors = ex.bindingResult.fieldErrors.map {
                FieldValidationError(
                    it.field,
                    it.defaultMessage ?: "Invalid"
                )
            }
        )
    }
}