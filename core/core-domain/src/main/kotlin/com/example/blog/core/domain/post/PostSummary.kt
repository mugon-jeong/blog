package com.example.blog.core.domain.post

import java.time.LocalDateTime
import java.util.UUID

data class PostSummary(
    val id: UUID,
    val title: String,
    val writer: String,
    val createdAt: LocalDateTime,
)
