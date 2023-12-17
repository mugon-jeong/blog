package com.example.blog.core.domain.post

import com.example.blog.core.domain.support.DomainPage
import com.example.blog.core.domain.support.DomainSort
import com.example.blog.core.domain.support.Storage
import java.util.UUID

@Storage
class PostFinder(
    private val postRepository: PostRepository,
) {
    fun findById(id: UUID): Post? = postRepository.findById(id)

    fun findPage(
        pageNumber: Int,
        pageSize: Int,
        domainSort: DomainSort,
    ): DomainPage<Post> =
        postRepository.findPage(
            pageNumber,
            pageSize,
            domainSort,
        )
}
