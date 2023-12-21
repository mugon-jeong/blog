package com.example.blog.core.api.controller.v1.site

import com.example.blog.core.api.security.loginMemberId
import com.example.blog.core.api.support.response.ApiResponse
import com.example.blog.domain.site.Site
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/site")
class SiteController(
    private val siteService: SiteService,
) {
    @PostMapping
    fun append(
        @RequestBody request: SiteAppendRequest,
    ): ApiResponse<UUID> {
        val result = siteService.append(loginMemberId(), request.toDomain())
        return ApiResponse.success(result)
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: UUID,
    ): ApiResponse<Site> {
        return ApiResponse.success(siteService.findById(id))
    }
}
