package com.example.blog.core.enums

enum class Permission(val roleName: String) {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    SITE_ACCESS("SITE_ACCESS"),
    SITE_DENY("SITE_DENY"),
}
