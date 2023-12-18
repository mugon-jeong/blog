package com.example.blog.core.api.security

import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.core.api.support.response.ApiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?,
    ) {
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/json; charset=utf-8"
        response.writer.write(objectMapper.writeValueAsString(ApiResponse.error<Any>(ErrorType.ACCESS_DENY, null)))
        response.writer.flush()
    }
}
