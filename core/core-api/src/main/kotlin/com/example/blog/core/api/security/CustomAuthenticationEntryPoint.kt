package com.example.blog.core.api.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?,
    ) {
        response?.status = HttpStatus.FORBIDDEN.value()
        response?.contentType = "application/json; charset=utf-8"
        response?.writer?.write("Token not Found")
        response?.writer?.flush()
    }
}
