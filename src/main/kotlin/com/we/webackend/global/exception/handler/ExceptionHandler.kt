package com.we.webackend.global.exception.handler

import com.we.webackend.global.exception.data.ErrorResponse
import com.we.webackend.global.exception.data.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@RestControllerAdvice
class ExceptionHandler {


    @ExceptionHandler(BusinessException::class)
    fun globalExceptionHandler(error: BusinessException): ResponseEntity<*> {
        return ResponseEntity.status(error.errorCode.status).body(
            ErrorResponse(
                error.errorCode.code,
                error.data.toString()
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                e.bindingResult.allErrors[0].defaultMessage.toString(),
                e.bindingResult.allErrors[0].arguments?.get(0).toString()
            )
        )
    }

}
