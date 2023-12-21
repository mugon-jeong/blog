package com.example.blog.core.api.controller.v1.site

import com.example.blog.core.api.support.error.CoreApiException
import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.domain.site.Site
import com.example.blog.domain.site.SiteAppendContent
import com.example.blog.domain.site.SiteAppender
import com.example.blog.domain.site.SiteFinder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class SiteService(
    private val siteAppender: SiteAppender,
    private val siteFinder: SiteFinder,
) {
    @Transactional
    fun append(
        owner: UUID,
        siteAppendContent: SiteAppendContent,
    ): UUID {
        return siteAppender.append(owner, siteAppendContent)
    }

    fun findById(id: UUID): Site {
        return siteFinder.findById(id) ?: throw CoreApiException(ErrorType.SITE_NOT_FOUND_ERROR)
    }
}
