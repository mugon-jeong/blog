package com.example.blog.storage.db.core.post

import com.example.blog.core.domain.post.PostContent
import com.example.blog.core.domain.post.PostRepository
import com.example.blog.storage.db.core.member.MemberJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PostEntityRepository(
    private val postJpaRepository: PostJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : PostRepository {
    override fun save(blog: PostContent): UUID {
        val writer = memberJpaRepository.getReferenceById(blog.writer)
        return postJpaRepository.save(blog.toEntity(writer)).id
    }
}
