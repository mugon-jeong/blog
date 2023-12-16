package com.example.blog.core.domain.post

import com.example.blog.core.domain.support.Storage
import java.util.UUID

@Storage
class PostUpdater(
    private val postRepository: PostRepository,
) {
    fun update(
        id: UUID,
        title: String,
        content: String,
    ): UUID {
        return postRepository.update(id, title, content)
    }
}
