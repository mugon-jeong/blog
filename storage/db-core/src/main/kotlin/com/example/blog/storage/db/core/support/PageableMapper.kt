package com.example.blog.storage.db.core.support

import com.example.blog.core.domain.support.DomainPageable
import org.springframework.data.domain.Page

fun <T> Page<T>.toDomainPageable() =
    DomainPageable(
        first = this.isFirst,
        last = this.isLast,
        hasNext = this.hasNext(),
        page = this.number,
        totalPages = this.totalPages,
        totalElements = this.totalElements,
    )
