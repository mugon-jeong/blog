package com.example.blog.core.domain.post

import java.util.UUID

data class Post(
    val id: UUID,
    val title: String,
    val content: String,
    val writer: Writer,
    val comments: List<PostComment>,
)
