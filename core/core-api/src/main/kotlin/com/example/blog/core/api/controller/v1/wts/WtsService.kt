package com.example.blog.core.api.controller.v1.wts

import com.example.blog.core.api.support.error.CoreApiException
import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.domain.wts.Wts
import com.example.blog.domain.wts.WtsAppender
import com.example.blog.domain.wts.WtsFinder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class WtsService(
    private val wtsAppender: WtsAppender,
    private val wtsFinder: WtsFinder,
) {
    @Transactional
    fun append(
        siteId: UUID,
        name: String,
    ): UUID {
        return wtsAppender.append(siteId, name)
    }

    fun findById(
        siteId: UUID,
        wtsId: UUID,
    ): Wts {
        return wtsFinder.findBySiteAndId(siteId, wtsId) ?: throw CoreApiException(ErrorType.WTS_NOT_FOUND_ERROR)
    }
}
