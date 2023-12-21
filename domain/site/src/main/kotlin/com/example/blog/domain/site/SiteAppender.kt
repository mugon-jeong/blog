package com.example.blog.domain.site

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SiteAppender(
    private val siteRepository: SiteRepository,
) {
    fun append(
        owner: UUID,
        siteAppendContent: SiteAppendContent,
    ): UUID {
        return siteRepository.append(owner, siteAppendContent)
    }
}
