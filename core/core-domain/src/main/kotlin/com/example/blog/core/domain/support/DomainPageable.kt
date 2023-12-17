package com.example.blog.core.domain.support

data class DomainPageable(
    val first: Boolean = false,
    val last: Boolean = false,
    val hasNext: Boolean = false,
    val page: Int = 0,
    val totalPages: Int = 0,
    val totalElements: Long = 0,
)
