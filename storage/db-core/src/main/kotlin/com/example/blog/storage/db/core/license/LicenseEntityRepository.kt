package com.example.blog.storage.db.core.license

import com.example.blog.core.domain.license.LicenseRepository
import com.example.blog.storage.db.core.member.MemberJpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class LicenseEntityRepository(
    private val licenseJpaRepository: LicenseJpaRepository,
    private val memberJpaRepository: MemberJpaRepository,
) : LicenseRepository {
    override fun append(): UUID {
        return licenseJpaRepository.save(LicenseEntity()).id
    }

    override fun grant(
        licenseId: UUID,
        targetId: UUID,
    ): UUID {
        val license = licenseJpaRepository.findById(licenseId).get()
        val member = memberJpaRepository.findById(targetId).get()
        member.grant(license)
        return member.id
    }
}
