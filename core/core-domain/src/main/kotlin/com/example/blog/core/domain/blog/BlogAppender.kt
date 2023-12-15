package com.example.blog.core.domain.blog

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class BlogAppender(
    private val blogRepository: BlogRepository,
) {
    fun append(blog: BlogContent): UUID {
        return blogRepository.save(blog)
    }
}
