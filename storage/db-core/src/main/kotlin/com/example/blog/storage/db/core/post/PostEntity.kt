package com.example.blog.storage.db.core.post

import com.example.blog.storage.db.core.PrimaryKey
import com.example.blog.storage.db.core.member.MemberEntity
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.util.UUID

private val logger = KotlinLogging.logger { }

@Entity
@Table(name = "post")
class PostEntity(
    title: String,
    content: String,
    writer: MemberEntity,
) : PrimaryKey() {
    @Comment("제목")
    @Column(name = "title", length = 50, nullable = false)
    var title: String = title

    @Comment("내용")
    @Column(name = "content", columnDefinition = "TEXT")
    var content: String = content

    @Comment("작성자")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var writer: MemberEntity = writer
        protected set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "post")
    private val mutableComments: MutableList<PostCommentEntity> = mutableListOf()
    val comments: List<PostCommentEntity> get() = mutableComments.toList()

    fun addComment(comment: PostCommentEntity) {
        mutableComments.add(comment)
    }

    fun deleteComment(commentId: UUID) {
        mutableComments.removeIf {
            it.id == commentId
        }
    }

    fun updateComment(
        commentId: UUID,
        content: String,
    ) {
        mutableComments.find { it.id == commentId }?.content = content
    }

    fun update(
        title: String,
        content: String,
    ) {
        this.title = title
        this.content = content
    }

    /**
     * this' 누수 경고는 객체 초기화가 완료되기 전에 다른 메서드에게 현재 객체의 참조를 제공할 때 발생하는 것을 의미
     * 여기서는 객체 초기화가 완료된 후에 writer.writeBoard(this)가 호출되므로 이 부분에서 발생하는 누수는 아님
     */
    init {
        writer.writeBoard(this)
    }
}
