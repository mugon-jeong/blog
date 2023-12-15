package com.example.blog.core.domain.blog

import java.util.UUID

interface BlogRepository {
    fun save(blog: BlogContent): UUID
}
