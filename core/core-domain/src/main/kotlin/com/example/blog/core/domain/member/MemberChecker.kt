package com.example.blog.core.domain.member

import org.springframework.stereotype.Component

@Component
class MemberChecker(
    private val memberRepository: MemberRepository,
) {
    fun duplicateMemberId(memberId: String): Boolean {
        return memberRepository.duplicateMemberId(memberId)
    }
}
