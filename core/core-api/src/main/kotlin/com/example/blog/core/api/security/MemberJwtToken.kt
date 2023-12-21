package com.example.blog.core.api.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import java.util.UUID

class MemberJwtToken(val id: UUID, jwt: Jwt, authorities: Collection<GrantedAuthority>) : JwtAuthenticationToken(jwt, authorities)

private fun memberJwtToken() = SecurityContextHolder.getContext().authentication as MemberJwtToken

fun loginMemberId(): UUID = memberJwtToken().id
