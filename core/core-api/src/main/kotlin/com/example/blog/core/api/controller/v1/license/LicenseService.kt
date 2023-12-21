package com.example.blog.core.api.controller.v1.license

import com.example.blog.core.domain.license.LicenseAppender
import com.example.blog.core.domain.license.LicenseGrant
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class LicenseService(
    private val licenseAppender: LicenseAppender,
    private val licenseGrant: LicenseGrant,
) {
    @Transactional
    fun append(): UUID {
        return licenseAppender.append()
    }

    @Transactional
    fun grant(
        licenseId: UUID,
        targetId: UUID,
    ): UUID {
        return licenseGrant.grant(licenseId, targetId)
    }
}
