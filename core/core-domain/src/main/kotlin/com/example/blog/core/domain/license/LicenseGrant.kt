package com.example.blog.core.domain.license

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class LicenseGrant(
    private val licenseRepository: LicenseRepository,
) {
    fun grant(
        licenseId: UUID,
        targetId: UUID,
    ): UUID {
        return licenseRepository.grant(licenseId, targetId)
    }
}
