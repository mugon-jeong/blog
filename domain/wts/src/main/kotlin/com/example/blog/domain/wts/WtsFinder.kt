package com.example.blog.domain.wts

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class WtsFinder(
    private val wtsRepository: WtsRepository,
) {
    fun findBySiteAndId(
        siteId: UUID,
        wtsId: UUID,
    ): Wts? {
        return wtsRepository.findBySiteAndId(siteId, wtsId)
    }
}
