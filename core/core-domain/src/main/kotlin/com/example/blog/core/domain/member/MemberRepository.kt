package com.example.blog.core.domain.member

import java.util.UUID

interface MemberRepository {
    fun save(member: MemberSignUp): UUID

    fun findByIdOrNull(memberId: UUID): Member?

    fun findByIdAndPw(
        memberId: String,
        memberPw: String,
    ): Member?

    fun findByMemberId(memberId: String): Member?

    fun duplicateMemberId(memberId: String): Boolean
}
