package com.example.blog.core.domain.post

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PostAppender(
    private val postRepository: PostRepository,
) {
    fun append(post: PostContent): UUID {
        return postRepository.save(post)
    }
}
