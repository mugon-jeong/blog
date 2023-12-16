package com.example.blog.storage.db.core.post

import com.example.blog.storage.db.core.PrimaryKey
import com.example.blog.storage.db.core.member.MemberEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "post_comment")
class PostCommentEntity(
    content: String,
    writer: MemberEntity,
    post: PostEntity,
) : PrimaryKey() {
    @Column(length = 3000)
    var content: String = content

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var writer: MemberEntity = writer

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var post: PostEntity = post
}
