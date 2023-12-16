package com.example.blog.core.api.controller.v1.post

import com.example.blog.core.api.support.error.CoreApiException
import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.core.domain.post.PostAppender
import com.example.blog.core.domain.post.PostContent
import com.example.blog.core.domain.post.PostFinder
import com.example.blog.core.domain.post.PostUpdater
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class PostService(
    private val postAppender: PostAppender,
    private val postFinder: PostFinder,
    private val postUpdater: PostUpdater,
) {
    @Transactional
    fun appender(post: PostContent): UUID {
        return postAppender.append(post)
    }

    fun findById(id: UUID) = postFinder.findById(id) ?: throw CoreApiException(ErrorType.POST_NOT_FOUND_ERROR)

    @Transactional
    fun updater(
        postId: UUID,
        updaterId: UUID,
        title: String,
        content: String,
    ): UUID {
        val post = postFinder.findById(postId) ?: throw CoreApiException(ErrorType.POST_NOT_FOUND_ERROR)
        if (post.writer.id != updaterId) throw CoreApiException(ErrorType.POST_EDIT_PERMISSION_DENIED)
        return postUpdater.update(post.id, title, content)
    }
}
