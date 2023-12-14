package com.example.blog.core.domain.member

import java.util.UUID

interface MemberRepository {
    fun save(member: MemberSignUp): UUID
}
