package com.example.blog.core.domain.member

import java.util.UUID

data class Member(
    val id: UUID,
    val memberId: String,
    val memberPw: String,
)
