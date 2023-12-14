package com.example.blog.core.api.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken
import org.springframework.security.web.access.AccessDeniedHandler
import java.io.IOException

class CustomAccessDeniedHandler() : AccessDeniedHandler {
    private val objectMapper: ObjectMapper = ObjectMapper()

    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException,
    ) {
        val parameters: MutableMap<String, String?> = LinkedHashMap()
        parameters["from"] = "CustomAccessDeniedHandler"
        if (request.userPrincipal is AbstractOAuth2TokenAuthenticationToken<*>) {
            parameters["error"] = BearerTokenErrorCodes.INSUFFICIENT_SCOPE
            parameters["error_description"] = "The request requires higher privileges than provided by the access token."
        }
        parameters["error_ms"] = accessDeniedException.message
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = "application/json; charset=utf-8"
        response.writer.write(objectMapper.writeValueAsString(parameters))
        response.writer.flush()
    }
}
