package com.example.blog.core.api.controller.v1.member

import com.example.blog.core.api.support.error.CoreApiException
import com.example.blog.core.api.support.error.ErrorType
import com.example.blog.core.domain.member.MemberAppender
import com.example.blog.core.domain.member.MemberChecker
import com.example.blog.core.domain.member.MemberSignUp
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberAppender: MemberAppender,
    private val memberChecker: MemberChecker,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun appender(member: MemberSignUp): UUID {
        if (memberChecker.duplicateMemberId(memberId = member.memberId)) {
            throw CoreApiException(ErrorType.MEMBER_ID_DUPLICATE_ERROR)
        }
        val encodePw = passwordEncoder.encode(member.memberPw)
        val saveMember = member.copy(memberPw = encodePw)
        return memberAppender.appender(saveMember)
    }
}
