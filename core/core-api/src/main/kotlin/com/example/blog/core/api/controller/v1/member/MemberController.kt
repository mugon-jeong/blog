package com.example.blog.core.api.controller.v1.member

import com.example.blog.core.api.security.loginMemberId
import com.example.blog.core.api.support.response.ApiResponse
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
    ): ApiResponse<UUID> {
        val result = memberService.appender(member.toDomain())
        return ApiResponse.success(result)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
    ): ApiResponse<Map<String, String>> {
        val result = loginService.login(request.memberId, request.memberPw)
        return ApiResponse.success(result)
    }

    @GetMapping("/myInfo")
    fun myInfo(): UUID? {
        return loginMemberId()
    }
}
