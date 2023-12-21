package com.example.blog.domain.wts

import java.util.UUID

interface WtsRepository {
    fun append(
        siteId: UUID,
        name: String,
    ): UUID

    fun findBySiteAndId(
        siteId: UUID,
        wtsId: UUID,
    ): Wts?
}
