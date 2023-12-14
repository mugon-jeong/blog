package com.example.blog.core.api.v1.member

import com.example.blog.core.api.security.JwtService
import com.example.blog.core.enums.Permission
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LoginService(
    private val jwtService: JwtService,
) {
    fun login(): Map<String, String> {
        val id = UUID.randomUUID()
        val accessToken = jwtService.generateAccessToken(Permission.USER, id)
        val refreshToken = jwtService.generateRefreshToken(Permission.USER, id)
        return mapOf("accessToken" to accessToken, "refreshToken" to refreshToken)
    }
}
