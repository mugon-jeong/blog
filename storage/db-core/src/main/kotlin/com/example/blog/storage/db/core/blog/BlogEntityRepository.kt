package com.example.blog.storage.db.core.blog

import com.example.blog.core.domain.blog.BlogContent
import com.example.blog.core.domain.blog.BlogRepository
import com.example.blog.storage.db.core.member.MemberJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class BlogEntityRepository(
    private val blogJpaRepository: BlogJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : BlogRepository {
    override fun save(blog: BlogContent): UUID {
        val writer = memberJpaRepository.getReferenceById(blog.writer)
        return blogJpaRepository.save(blog.toEntity(writer)).id
    }
}
