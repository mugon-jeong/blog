package com.example.blog.storage.db.core.post

import com.example.blog.core.domain.post.PostContent
import com.example.blog.storage.db.core.member.MemberEntity

fun PostContent.toEntity(member: MemberEntity) =
    PostEntity(
        title = title,
        content = content,
        writer = member,
    )
