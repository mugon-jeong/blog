package com.example.blog.core.domain.support

data class DomainPage<T>(
    val content: List<T>,
    val pageable: DomainPageable,
)
