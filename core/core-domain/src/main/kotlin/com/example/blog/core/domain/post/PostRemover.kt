package com.example.blog.core.domain.post

import com.example.blog.core.domain.support.Storage
import java.util.UUID

@Storage
class PostRemover(
    private val postRepository: PostRepository,
) {
    fun removeById(postId: UUID): UUID {
        return postRepository.deleteById(postId)
    }

    fun removeComment(
        postId: UUID,
        commentId: UUID,
    ): UUID {
        return postRepository.removeComment(postId, commentId)
    }
}
