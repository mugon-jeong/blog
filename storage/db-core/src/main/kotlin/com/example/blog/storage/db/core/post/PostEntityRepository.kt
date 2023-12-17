package com.example.blog.storage.db.core.post

import com.example.blog.core.domain.post.Post
import com.example.blog.core.domain.post.PostContent
import com.example.blog.core.domain.post.PostRepository
import com.example.blog.core.domain.support.DomainPage
import com.example.blog.core.domain.support.DomainSort
import com.example.blog.storage.db.core.member.MemberJpaRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

private val logger = KotlinLogging.logger { }

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

    override fun findPage(
        pageNumber: Int,
        pageSize: Int,
        domainSort: DomainSort,
    ): DomainPage<Post> {
        logger.info { "findPage: pageNumber=$pageNumber, pageSize=$pageSize, postSort=$domainSort" }
        val sort =
            when (domainSort.isAsc) {
                true -> Sort.by(Sort.Order.asc(domainSort.sort.field))
                false -> Sort.by(Sort.Order.desc(domainSort.sort.field))
            }
        val pageable = PageRequest.of(pageNumber, pageSize, sort)

        return postJpaRepository.findPage(pageable) {
            select(
                entity(PostEntity::class),
            ).from(
                entity(PostEntity::class),
            )
        }.toDomain()
    }
}
