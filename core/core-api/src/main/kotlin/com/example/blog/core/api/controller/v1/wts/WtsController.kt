package com.example.blog.core.api.controller.v1.wts

import com.example.blog.core.api.support.response.ApiResponse
import com.example.blog.domain.wts.Wts
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/site/{siteId}/wts")
class WtsController(
    private val wtsService: WtsService,
) {
    @PostMapping
    fun append(
        @PathVariable siteId: UUID,
        @RequestBody request: WtsAppendRequest,
    ): ApiResponse<UUID> {
        return ApiResponse.success(wtsService.append(siteId, request.name))
    }

    @GetMapping("/{wtsId}")
    fun findById(
        @PathVariable siteId: UUID,
        @PathVariable wtsId: UUID,
    ): ApiResponse<Wts> {
        return ApiResponse.success(wtsService.findById(siteId, wtsId))
    }
}
