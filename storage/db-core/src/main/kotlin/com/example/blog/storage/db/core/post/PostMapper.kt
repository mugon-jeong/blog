package com.example.blog.storage.db.core.post

import com.example.blog.core.domain.post.Post
import com.example.blog.core.domain.post.PostComment
import com.example.blog.core.domain.post.PostContent
import com.example.blog.storage.db.core.member.MemberEntity
import com.example.blog.storage.db.core.member.toWriter

fun PostContent.toEntity(member: MemberEntity) =
    PostEntity(
        title = title,
        content = content,
        writer = member,
    )

fun PostEntity.toDomain() = Post(
    id = id,
    title = title,
    content = content,
    writer = writer.toWriter(),
    comments = comments.map { it.toDomain() },
)

fun PostCommentEntity.toDomain() = PostComment(
    id = id,
    content = content,
    writer = writer.toWriter(),
)
