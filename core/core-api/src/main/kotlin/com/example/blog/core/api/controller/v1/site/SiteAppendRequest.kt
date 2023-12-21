package com.example.blog.core.api.controller.v1.site

import com.example.blog.domain.site.SiteAppendContent

data class SiteAppendRequest(
    val name: String,
    val address: String,
)

fun SiteAppendRequest.toDomain() =
    SiteAppendContent(
        name = name,
        address = address,
    )
