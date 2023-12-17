package com.example.blog.core.domain.support

import com.example.blog.core.enums.PostSort

data class DomainSort(
    val sort: PostSort,
    val isAsc: Boolean,
)
