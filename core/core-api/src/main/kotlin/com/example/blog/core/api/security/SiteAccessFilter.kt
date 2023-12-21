package com.example.blog.core.api.security

import com.example.blog.core.domain.license.LicenseFinder
import com.example.blog.core.enums.Permission
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter

private val log = KotlinLogging.logger { }

class SiteAccessFilter(
    private val licenseFinder: LicenseFinder,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        log.info { "SiteAccessFilter" }
        val authentication = SecurityContextHolder.getContext().authentication
        log.info { "authentication: ${authentication.authorities.map { it.authority }}" }
        if ((authentication is JwtAuthenticationToken)) {
            log.info { "authentication is JwtAuthenticationToken" }
        }
        if ((authentication is MemberJwtToken) && authentication.authorities.map { it.authority }.contains(Permission.USER.name)) {
            val license = licenseFinder.findLicenseInMember(authentication.id)
            log.info { "license: $license" }
            if (license != null) {
                val newAuthorities = authentication.authorities.toMutableList()
                newAuthorities.add(SimpleGrantedAuthority(Permission.SITE_ACCESS.roleName))
                val newAuthentication = MemberJwtToken(authentication.id, authentication.token, newAuthorities)
                SecurityContextHolder.getContext().authentication = newAuthentication
                log.info { "authentication: ${newAuthorities.map { it.authority }}" }
            }
        }
        filterChain.doFilter(request, response)
    }
}
