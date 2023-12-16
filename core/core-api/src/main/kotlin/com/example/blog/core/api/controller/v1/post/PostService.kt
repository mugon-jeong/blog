package com.example.blog.core.api.controller.v1.post

import com.example.blog.core.domain.post.PostAppender
import com.example.blog.core.domain.post.PostContent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class PostService(
    private val postAppender: PostAppender,
) {
    @Transactional
    fun appender(post: PostContent): UUID {
        return postAppender.append(post)
    }
}
