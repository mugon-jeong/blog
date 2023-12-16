package com.example.blog.storage.db.core.post

import com.example.blog.core.domain.post.Post
import com.example.blog.core.domain.post.PostContent
import com.example.blog.core.domain.post.PostRepository
import com.example.blog.storage.db.core.member.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Repository
@Transactional
class PostEntityRepository(
    private val postJpaRepository: PostJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : PostRepository {
    override fun save(post: PostContent): UUID {
        val writer = memberJpaRepository.getReferenceById(post.writer)
        return postJpaRepository.save(post.toEntity(writer)).id
    }

    override fun findById(id: UUID): Post? = postJpaRepository.findByIdOrNull(id)?.toDomain()

    override fun update(
        id: UUID,
        title: String,
        content: String,
    ): UUID {
        val postEntity = postJpaRepository.getReferenceById(id)
        postEntity.update(title, content)
        return postEntity.id
    }
}