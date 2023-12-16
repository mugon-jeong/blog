package com.example.blog.storage.db.core.member

import com.example.blog.core.domain.member.Member
import com.example.blog.core.domain.member.MemberRepository
import com.example.blog.core.domain.member.MemberSignUp
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Repository
class MemberEntityRepository(
    private val memberJpaRepository: MemberJpaRepository,
) : MemberRepository {
    override fun save(member: MemberSignUp): UUID {
        return memberJpaRepository.save(member.toEntity()).id
    }

    override fun findByIdOrNull(memberId: UUID): Member? {
        return memberJpaRepository.findByIdOrNull(memberId)?.toDomain()
    }

    override fun findByIdAndPw(
        memberId: String,
        memberPw: String,
    ): Member? {
        return memberJpaRepository.findAll {
            select(
                entity(MemberEntity::class),
            ).from(
                entity(MemberEntity::class),
            ).where(
                path(MemberEntity::memberId).eq(memberId)
                    .and(path(MemberEntity::memberPw).eq(memberPw)),
            )
        }.firstOrNull()?.toDomain()
    }

    override fun findByMemberId(memberId: String): Member? {
        return memberJpaRepository.findAll {
            select(
                entity(MemberEntity::class),
            ).from(
                entity(MemberEntity::class),
            ).where(
                path(MemberEntity::memberId).eq(memberId),
            )
        }.firstOrNull()?.toDomain()
    }

    override fun duplicateMemberId(memberId: String): Boolean {
        return memberJpaRepository.findAll {
            select(
                entity(MemberEntity::class),
            ).from(
                entity(MemberEntity::class),
            ).where(
                path(MemberEntity::memberId).eq(memberId),
            )
        }.isNotEmpty()
    }
}
