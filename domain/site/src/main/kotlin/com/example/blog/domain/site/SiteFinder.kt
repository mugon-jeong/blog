package com.example.blog.domain.site

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SiteFinder(
    private val siteRepository: SiteRepository,
) {
    fun findById(id: UUID): Site? {
        return siteRepository.findById(id)
    }
}
