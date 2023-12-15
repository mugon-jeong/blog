package com.example.blog.core.api.v1.blog

import com.example.blog.core.domain.blog.BlogAppender
import com.example.blog.core.domain.blog.BlogContent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class BlogService(
    private val blogAppender: BlogAppender,
) {
    @Transactional
    fun appender(blog: BlogContent): UUID {
        return blogAppender.append(blog)
    }
}
