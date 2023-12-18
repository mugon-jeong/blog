package com.example.blog.core.domain.post

import com.example.blog.core.domain.support.DomainPage
import com.example.blog.core.domain.support.DomainSort
import java.util.UUID

interface PostRepository {
    fun save(post: PostContent): UUID

    fun findById(id: UUID): Post?

    fun update(
        id: UUID,
        title: String,
        content: String,
    ): UUID

    fun findPage(
        pageNumber: Int,
        pageSize: Int,
        domainSort: DomainSort,
    ): DomainPage<PostSummary>

    fun deleteById(postId: UUID): UUID
}
