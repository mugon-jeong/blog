package com.example.blog.core.domain.member

import com.example.blog.core.domain.support.Storage

@Storage
class MemberChecker(
    private val memberRepository: MemberRepository,
) {
    fun duplicateMemberId(memberId: String): Boolean {
        return memberRepository.duplicateMemberId(memberId)
    }
}
