package com.example.blog.domain.site

import java.util.UUID

interface SiteRepository {
    fun append(
        owner: UUID,
        siteAppendContent: SiteAppendContent,
    ): UUID

    fun findById(id: UUID): Site?
}
