package com.example.blog.core.api.controller.v1.post

import com.example.blog.core.api.security.loginMemberId
import com.example.blog.core.api.support.response.ApiResponse
import com.example.blog.core.domain.post.Post
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/post")
class PostController(
    private val postService: PostService,
) {
    @PostMapping
    fun save(
        @RequestBody request: PostAppendRequest,
    ): ApiResponse<UUID> {
        val result = postService.appender(request.toDomain(loginMemberId()))
        return ApiResponse.success(result)
    }

    @GetMapping("/{postId}")
    fun getById(
        @PathVariable postId: String,
    ): ApiResponse<Post> {
        val post = postService.findById(UUID.fromString(postId))
        return ApiResponse.success(post)
    }
}
