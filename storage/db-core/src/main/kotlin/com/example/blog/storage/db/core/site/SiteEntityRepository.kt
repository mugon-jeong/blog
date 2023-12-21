package com.example.blog.storage.db.core.site

import com.example.blog.domain.site.Site
import com.example.blog.domain.site.SiteAppendContent
import com.example.blog.domain.site.SiteRepository
import com.example.blog.storage.db.core.member.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class SiteEntityRepository(
    private val siteJpaRepository: SiteJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : SiteRepository {
    override fun append(
        owner: UUID,
        siteAppendContent: SiteAppendContent,
    ): UUID {
        val member = memberJpaRepository.getReferenceById(owner)
        return siteJpaRepository.save(siteAppendContent.toEntity(member)).id
    }

    override fun findById(id: UUID): Site? {
        return siteJpaRepository.findByIdOrNull(id)?.toDomain()
    }
}
