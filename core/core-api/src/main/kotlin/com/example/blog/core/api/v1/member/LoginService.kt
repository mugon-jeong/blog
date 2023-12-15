package com.example.blog.core.api.v1.member

import com.example.blog.core.api.security.JwtService
import com.example.blog.core.domain.member.MemberFinder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val jwtService: JwtService,
    private val memberFinder: MemberFinder,
    private val passwordEncoder: PasswordEncoder,
) {
    fun login(
        memberId: String,
        memberPw: String,
    ): Map<String, String> {
        val member = memberFinder.findByMemberId(memberId) ?: throw RuntimeException()
        val matches = passwordEncoder.matches(memberPw, member.memberPw)
        if (!matches) throw RuntimeException("비밀번호가 틀렸습니다.")
        val accessToken = jwtService.generateAccessToken(member.permission, member.id)
        val refreshToken = jwtService.generateRefreshToken(member.permission, member.id)
        return mapOf("accessToken" to accessToken, "refreshToken" to refreshToken)
    }
}
