package com.example.blog.core.domain.member

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MemberFinder(
    private val memberRepository: MemberRepository,
) {
    fun findByIdOrNull(memberId: UUID): Member? {
        return memberRepository.findByIdOrNull(memberId)
    }

    fun findByIdAndPw(
        memberId: String,
        memberPw: String,
    ): Member? {
        return memberRepository.findByIdAndPw(memberId, memberPw)
    }

    fun findByMemberId(memberId: String): Member? {
        return memberRepository.findByMemberId(memberId)
    }
}
