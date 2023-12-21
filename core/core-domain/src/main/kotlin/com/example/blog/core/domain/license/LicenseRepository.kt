package com.example.blog.core.domain.license

import java.util.UUID

interface LicenseRepository {
    fun append(): UUID

    fun grant(
        licenseId: UUID,
        targetId: UUID,
    ): UUID
}
