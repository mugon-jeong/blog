package com.example.blog.core.domain.member

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MemberAppender(
    private val memberRepository: MemberRepository,
) {
    fun appender(member: MemberSignUp): UUID {
        return memberRepository.save(member)
    }
}
