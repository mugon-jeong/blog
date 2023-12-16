package com.example.blog.core.api.controller.v1.post

data class PostUpdateRequest(
    val title: String,
    val content: String,
)
