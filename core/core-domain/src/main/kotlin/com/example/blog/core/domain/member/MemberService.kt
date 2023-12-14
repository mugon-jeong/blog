package com.example.blog.core.domain.member

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MemberService(
    private val memberAppender: MemberAppender,
) {
    fun appender(member: MemberSignUp): UUID {
        return memberAppender.appender(member)
    }
}
