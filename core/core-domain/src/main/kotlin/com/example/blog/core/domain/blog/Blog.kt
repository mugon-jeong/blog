package com.example.blog.core.domain.blog

import java.util.UUID

data class Blog(
    val id: UUID,
    val title: String,
    val content: String,
    val writer: Writer,
    val comments: List<BlogComment>,
)
