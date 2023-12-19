package com.example.blog.core.api.controller.v1.post

import com.example.blog.core.api.security.loginMemberId
import com.example.blog.core.api.support.response.ApiResponse
import com.example.blog.core.domain.post.Post
import com.example.blog.core.domain.post.PostSummary
import com.example.blog.core.domain.support.DomainPage
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @PutMapping("/{postId}")
    fun update(
        @PathVariable postId: String,
        @RequestBody request: PostUpdateRequest,
    ): ApiResponse<UUID> {
        val updated = postService.updater(UUID.fromString(postId), loginMemberId(), request.title, request.content)
        return ApiResponse.success(updated)
    }

    @DeleteMapping("/{postId}")
    fun deleteById(
        @PathVariable postId: String,
    ): ApiResponse<UUID> {
        val deleted = postService.deleteById(UUID.fromString(postId), loginMemberId())
        return ApiResponse.success(deleted)
    }

    @GetMapping
    fun findPage(pageable: Pageable): ApiResponse<DomainPage<PostSummary>> {
        val result = postService.findPage(pageable)
        return ApiResponse.success(result)
    }

    @PostMapping("/{postId}/comments")
    fun addComment(
        @PathVariable postId: UUID,
        @RequestBody request: PostCommentAppendRequest,
    ): ApiResponse<UUID> {
        val result = postService.addComment(postId, loginMemberId(), request.content)
        return ApiResponse.success(result)
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    fun deleteComment(
        @PathVariable postId: UUID,
        @PathVariable commentId: UUID,
    ): UUID {
        val result = postService.removeComment(postId = postId, commentId = commentId, writer = loginMemberId())
        return result
    }

    @PutMapping("/{postId}/comments/{commentId}")
    fun updateComment(
        @PathVariable postId: UUID,
        @PathVariable commentId: UUID,
        @RequestBody request: PostCommentAppendRequest,
    ): UUID {
        val result = postService.updateComment(postId, commentId, loginMemberId(), request.content)
        return result
    }
}
