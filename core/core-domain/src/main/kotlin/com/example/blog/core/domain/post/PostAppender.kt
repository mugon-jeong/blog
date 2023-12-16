package com.example.blog.core.domain.post

import com.example.blog.core.domain.support.Storage
import java.util.UUID

@Storage
class PostAppender(
    private val postRepository: PostRepository,
) {
    fun append(post: PostContent): UUID {
        return postRepository.save(post)
    }
}
