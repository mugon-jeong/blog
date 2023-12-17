package com.example.blog.storage.db.core.post

import com.example.blog.core.domain.post.Post
import com.example.blog.core.domain.post.PostComment
import com.example.blog.core.domain.post.PostContent
import com.example.blog.core.domain.support.DomainPage
import com.example.blog.storage.db.core.member.MemberEntity
import com.example.blog.storage.db.core.member.toWriter
import com.example.blog.storage.db.core.support.toDomainPageable
import org.springframework.data.domain.Page

fun PostContent.toEntity(member: MemberEntity) =
    PostEntity(
        title = title,
        content = content,
        writer = member,
    )

fun PostEntity.toDomain() =
    Post(
        id = id,
        title = title,
        content = content,
        writer = writer.toWriter(),
        comments = comments.map { it.toDomain() },
        createdAt = createdAt,
    )

fun PostCommentEntity.toDomain() =
    PostComment(
        id = id,
        content = content,
        writer = writer.toWriter(),
    )

fun Page<PostEntity?>.toDomain(): DomainPage<Post> {
    val content =
        this.content
            .takeIf { it.isNotEmpty() }
            ?.mapNotNull { it?.toDomain() }
            .orEmpty()
    return DomainPage(
        content = content,
        pageable = this.toDomainPageable(),
    )
}
