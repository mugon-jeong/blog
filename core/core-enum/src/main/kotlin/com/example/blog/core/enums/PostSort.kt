package com.example.blog.core.enums

enum class PostSort(val field: String) {
    CREATED_AT("createdAt"),
    ;

    fun field() = field
}
