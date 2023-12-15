package com.example.blog.core.api.v1.member

data class LoginRequest(
    val memberId: String,
    val memberPw: String,
)
