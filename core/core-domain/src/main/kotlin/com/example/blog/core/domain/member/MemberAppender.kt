package com.example.blog.core.domain.member

import com.example.blog.core.domain.support.Storage
import java.util.UUID

@Storage
class MemberAppender(
    private val memberRepository: MemberRepository,
) {
    fun appender(member: MemberSignUp): UUID {
        return memberRepository.save(member)
    }
}
