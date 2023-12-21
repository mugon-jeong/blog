package com.example.blog.core.domain.license

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class LicenseFinder(
    private val licenseRepository: LicenseRepository,
) {
    @Transactional
    fun findLicenseInMember(memberId: UUID): License? {
        return licenseRepository.findByMemberId(memberId)
    }
}
