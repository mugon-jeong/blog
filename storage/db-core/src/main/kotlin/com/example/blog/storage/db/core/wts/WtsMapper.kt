package com.example.blog.storage.db.core.wts

import com.example.blog.domain.wts.Wts

fun WtsEntity.toDomain() =
    Wts(
        id = id,
        name = name,
    )
