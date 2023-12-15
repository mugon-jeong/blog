package com.example.blog.core.domain.blog

import java.util.UUID

data class BlogContent(
    val title: String,
    val content: String,
    val writer: UUID,
)
