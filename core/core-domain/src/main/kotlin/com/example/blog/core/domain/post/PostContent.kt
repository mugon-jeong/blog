package com.example.blog.core.domain.post

import java.util.UUID

data class PostContent(
    val title: String,
    val content: String,
    val writer: UUID,
)
