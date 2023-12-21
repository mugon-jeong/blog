package com.example.blog.core.api.security

import com.example.blog.core.enums.Permission
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

private val log = KotlinLogging.logger { }

class SiteAccessFilter(
    private val siteAccessService: SiteAccessService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        log.info { "SiteAccessFilter" }
        val authentication = SecurityContextHolder.getContext().authentication
        log.info { "authentication: ${authentication.authorities.map { it.authority }}" }

        val siteId = extractSiteIdFromPath(request.requestURI)
        log.info { "siteId: $siteId" }

        if (authentication is MemberJwtToken &&
            authentication.authorities.map { it.authority }.contains(Permission.USER.name)
        ) {
            val authorities = authentication.authorities.toMutableList()
            if (siteId == null) {
                addSiteAccessAuthorityIfLicenseExists(authorities, authentication.id)
            } else {
                addSiteAccessAndOwnerAuthoritiesIfOwner(authorities, siteId, authentication.id)
            }
            updateAuthentication(authentication, authorities)
        }

        filterChain.doFilter(request, response)
    }

    private fun extractSiteIdFromPath(pathInfo: String): String? {
        val siteIdPattern = "/api/v1/site/([0-9a-fA-F\\-]+)/wts".toRegex()
        val matchResult = siteIdPattern.find(pathInfo)
        return matchResult?.groups?.get(1)?.value
    }

    private fun addSiteAccessAuthorityIfLicenseExists(
        authorities: MutableList<GrantedAuthority>,
        userId: UUID,
    ) {
        val license = siteAccessService.findLicenseInMember(userId)
        if (license != null) {
            authorities.add(SimpleGrantedAuthority(Permission.SITE_ACCESS.roleName))
        }
    }

    private fun addSiteAccessAndOwnerAuthoritiesIfOwner(
        authorities: MutableList<GrantedAuthority>,
        siteId: String,
        userId: UUID,
    ) {
        val site = siteAccessService.findSiteById(siteId.toUUID())
        if (site != null && site.owner.id == userId) {
            authorities.add(SimpleGrantedAuthority(Permission.SITE_OWNER.roleName))
        }
        addSiteAccessAuthorityIfLicenseExists(authorities, userId)
    }

    private fun updateAuthentication(
        authentication: MemberJwtToken,
        newAuthorities: List<GrantedAuthority>,
    ) {
        val newAuthentication = MemberJwtToken(authentication.id, authentication.token, newAuthorities)
        SecurityContextHolder.getContext().authentication = newAuthentication
        log.info { "authentication: ${newAuthorities.map { it.authority }}" }
    }
}

private fun String.toUUID() = UUID.fromString(this)
