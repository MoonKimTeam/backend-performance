package com.performance.global.exception

import com.performance.global.constant.CommonResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<CommonResponse<Unit>> {
        logger.error(exception.message)
        return ResponseEntity(CommonResponse.fail(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(exception: BusinessException): ResponseEntity<CommonResponse<Unit>> {
        logger.error(exception.message)
        return ResponseEntity(CommonResponse.fail(exception.code), exception.code.httpStatus)
    }

    @ExceptionHandler(DomainException::class)
    fun handleDomainException(exception: DomainException): ResponseEntity<CommonResponse<Unit>> {
        logger.error(exception.message)
        return ResponseEntity(CommonResponse.fail(exception.code), exception.code.httpStatus)
    }
}