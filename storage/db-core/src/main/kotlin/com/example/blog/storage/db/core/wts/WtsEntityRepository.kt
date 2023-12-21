package com.example.blog.storage.db.core.wts

import com.example.blog.domain.wts.Wts
import com.example.blog.domain.wts.WtsRepository
import com.example.blog.storage.db.core.site.SiteJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class WtsEntityRepository(
    private val wtsJpaRepository: WtsJpaRepository,
    private val siteJpaRepository: SiteJpaRepository,
) : WtsRepository {
    override fun append(
        siteId: UUID,
        name: String,
    ): UUID {
        val site = siteJpaRepository.getReferenceById(siteId)
        val wts = WtsEntity(name, site)
        site.addWts(wts)
        return wts.id
    }

    override fun findBySiteAndId(
        siteId: UUID,
        wtsId: UUID,
    ): Wts? {
        val site = siteJpaRepository.getReferenceById(siteId)
        return wtsJpaRepository.findAll {
            select(entity(WtsEntity::class))
                .from(entity(WtsEntity::class))
                .where(path(WtsEntity::getId).eq(wtsId).and(path(WtsEntity::site).eq(site)))
        }.firstOrNull()?.toDomain()
    }
}
