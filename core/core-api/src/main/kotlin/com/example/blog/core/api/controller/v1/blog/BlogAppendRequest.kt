package com.example.blog.core.api.controller.v1.blog

import com.example.blog.core.domain.blog.BlogContent
import java.util.UUID

data class BlogAppendRequest(
    val title: String,
    val content: String,
)

fun BlogAppendRequest.toDomain(writer: UUID) =
    BlogContent(
        title = title,
        content = content,
        writer = writer,
    )
