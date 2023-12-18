package com.example.blog.core.api.security

import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.core.api.support.response.ApiResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.util.StringUtils

class CustomBearerTokenAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        // 토큰 없을때 InsufficientAuthenticationException
        val parameters: MutableMap<String, String> = LinkedHashMap()
        if (authException is OAuth2AuthenticationException) {
            val error = authException.error
            parameters["error"] = error.errorCode
            if (StringUtils.hasText(error.description)) {
                parameters["error_description"] = error.description
            }
        }
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/json; charset=utf-8"
        response.writer.write(objectMapper.writeValueAsString(ApiResponse.error<Any>(ErrorType.TOKEN_INVALID, parameters)))
        response.writer.flush()
    }
}
