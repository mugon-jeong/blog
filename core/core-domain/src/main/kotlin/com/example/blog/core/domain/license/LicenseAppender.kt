package com.example.blog.core.domain.license

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class LicenseAppender(
    private val licenseRepository: LicenseRepository,
) {
    fun append(): UUID {
        return licenseRepository.append()
    }
}
