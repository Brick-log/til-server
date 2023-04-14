package com.tenmm.tilserver.common.adapter.inbound.rest

import com.tenmm.tilserver.common.adapter.inbound.rest.model.ErrorResponse
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE + 1)
class UnKnownExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable::class)
    fun handleApiException(ex: Throwable): ErrorResponse {
        ex.printStackTrace()
        return ErrorResponse("dummy")
    }
}
