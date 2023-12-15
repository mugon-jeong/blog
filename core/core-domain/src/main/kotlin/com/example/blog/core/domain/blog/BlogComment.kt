package com.example.blog.core.domain.blog

import java.util.UUID

data class BlogComment(
    val id: UUID,
    val content: String,
    val writer: Writer,
)
