package com.example.blog.core.api.v1.member

import com.example.blog.core.api.security.loginMemberId
import com.example.blog.core.domain.member.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1")
class MemberController(
    private val memberService: MemberService,
    private val loginService: LoginService,
) {
    @PostMapping("/member")
    fun appender(
        @RequestBody member: MemberSignUpRequest,
    ): UUID {
        return memberService.appender(member.toDomain())
    }

    @GetMapping("/login")
    fun login(): Map<String, String> {
        return loginService.login()
    }

    @GetMapping("/myInfo")
    fun myInfo(): UUID? {
        return loginMemberId()
    }
}
