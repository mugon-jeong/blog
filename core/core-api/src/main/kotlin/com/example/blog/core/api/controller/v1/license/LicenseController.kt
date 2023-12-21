package com.example.blog.core.api.controller.v1.license

import com.example.blog.core.api.support.response.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/license")
class LicenseController(
    private val licenseService: LicenseService,
) {
    @PostMapping
    fun append(): ApiResponse<UUID> {
        return ApiResponse.success(licenseService.append())
    }

    @PostMapping("/{licenseId}/grant/{targetId}")
    fun grant(
        @PathVariable licenseId: UUID,
        @PathVariable targetId: UUID,
    ): ApiResponse<UUID> {
        return ApiResponse.success(licenseService.grant(licenseId, targetId))
    }
}
