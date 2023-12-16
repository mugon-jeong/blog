package com.example.blog.core.domain.post

import java.util.UUID

interface PostRepository {
    fun save(post: PostContent): UUID

    fun findById(id: UUID): Post?

    fun update(
        id: UUID,
        title: String,
        content: String,
    ): UUID
}