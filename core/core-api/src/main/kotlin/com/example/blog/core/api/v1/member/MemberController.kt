package com.example.blog.core.api.v1.member

import com.example.blog.core.domain.member.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1")
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping("/member")
    fun appender(
        @RequestBody member: MemberSignUpRequest,
    ): UUID {
        return memberService.appender(member.toDomain())
    }
}
