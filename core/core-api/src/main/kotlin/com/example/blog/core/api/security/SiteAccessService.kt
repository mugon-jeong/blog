package com.example.blog.core.api.security

import com.example.blog.core.domain.license.LicenseFinder
import com.example.blog.domain.site.SiteFinder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class SiteAccessService(
    private val licenseFinder: LicenseFinder,
    private val siteFinder: SiteFinder,
) {
    fun findSiteById(id: UUID) = siteFinder.findById(id)

    fun findLicenseInMember(id: UUID) = licenseFinder.findLicenseInMember(id)
}
