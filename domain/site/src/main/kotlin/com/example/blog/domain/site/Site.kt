package com.example.blog.domain.site

import java.util.UUID

data class Site(
    val id: UUID,
    val name: String,
    val address: String,
    val owner: Owner,
)
