package com.example.blog.storage.db.core.blog

import com.example.blog.core.domain.blog.BlogContent
import com.example.blog.storage.db.core.member.MemberEntity

fun BlogContent.toEntity(member: MemberEntity) =
    BlogEntity(
        title = title,
        content = content,
        writer = member,
    )
