package com.example.blog.storage.db.core.license

import com.example.blog.core.domain.license.License

fun LicenseEntity.toDomain() =
    License(
        id = id,
    )
