package com.example.blog.core.api.security

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import java.util.UUID

private val log = KotlinLogging.logger { }

class CustomJwtAuthenticationConverter(
    private val jwtAuthenticationConverter: JwtAuthenticationConverter,
) : Converter<Jwt?, AbstractAuthenticationToken?> {
    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        val token = jwtAuthenticationConverter.convert(jwt)
        val authorities = token!!.authorities
        return convert(jwt, authorities)
    }

    private fun convert(
        jwt: Jwt,
        authorities: Collection<GrantedAuthority>,
    ): JwtAuthenticationToken {
        log.info { "CustomJwtAuthenticationConverter" }
        val memberUUID = UUID.fromString(jwt.getClaimAsString("id"))
        log.info { "memberUUID: $memberUUID" }
        val token = MemberJwtToken(memberUUID, jwt, authorities)
        SecurityContextHolder.getContext().authentication = token
        return token
    }
}
