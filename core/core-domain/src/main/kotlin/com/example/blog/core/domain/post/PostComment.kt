package com.example.blog.core.domain.post

import java.util.UUID

data class PostComment(
    val id: UUID,
    val content: String,
    val writer: Writer,
)
