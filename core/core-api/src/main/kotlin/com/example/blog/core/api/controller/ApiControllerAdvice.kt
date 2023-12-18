package com.example.blog.core.api.controller

import com.example.blog.core.api.support.error.CoreApiException
import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.core.api.support.response.ApiResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger { }

@RestControllerAdvice
class ApiControllerAdvice {
    @ExceptionHandler(CoreApiException::class)
    fun handleCoreApiException(e: CoreApiException): ResponseEntity<ApiResponse<Any>> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error { "CoreApiException :  ${e.message} $e" }
            LogLevel.WARN -> log.warn { "CoreApiException :  ${e.message} $e" }
            else -> log.info { "CoreApiException : , ${e.message}, $e" }
        }
        return ResponseEntity(ApiResponse.error(e.errorType, e.data), e.errorType.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        log.error { "Exception : {} ${e.message} $e" }
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.status)
    }
}
