package com.example.blog.core.domain.post

import com.example.blog.core.domain.support.Storage
import java.util.UUID

@Storage
class PostFinder(
    private val postRepository: PostRepository,
) {
    fun findById(id: UUID): Post? = postRepository.findById(id)
}
