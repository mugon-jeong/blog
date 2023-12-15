package com.example.blog.core.api.v1.blog

import com.example.blog.core.api.security.loginMemberId
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/blog")
class BlogController(
    private val blogService: BlogService,
) {
    @PostMapping
    fun save(
        @RequestBody request: BlogAppendRequest,
    ): UUID {
        return blogService.appender(request.toDomain(loginMemberId()))
    }
}
