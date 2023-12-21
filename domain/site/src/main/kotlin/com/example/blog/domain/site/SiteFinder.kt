package com.example.blog.domain.site

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class SiteFinder(
    private val siteRepository: SiteRepository,
) {
    @Transactional
    fun findById(id: UUID): Site? {
        return siteRepository.findById(id)
    }
}
