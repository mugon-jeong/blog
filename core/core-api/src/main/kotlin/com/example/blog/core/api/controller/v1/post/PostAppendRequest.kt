package com.example.blog.core.api.controller.v1.post

import com.example.blog.core.domain.post.PostContent
import java.util.UUID

data class PostAppendRequest(
    val title: String,
    val content: String,
)

fun PostAppendRequest.toDomain(writer: UUID) =
    PostContent(
        title = title,
        content = content,
        writer = writer,
    )
