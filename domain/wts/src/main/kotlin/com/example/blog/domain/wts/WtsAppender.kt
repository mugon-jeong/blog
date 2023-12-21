package com.example.blog.domain.wts

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class WtsAppender(
    private val wtsRepository: WtsRepository,
) {
    fun append(
        siteId: UUID,
        name: String,
    ): UUID {
        return wtsRepository.append(siteId, name)
    }
}
