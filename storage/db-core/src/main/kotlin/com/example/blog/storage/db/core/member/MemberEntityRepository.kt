package com.example.blog.storage.db.core.member

import com.example.blog.core.domain.member.MemberRepository
import com.example.blog.core.domain.member.MemberSignUp
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class MemberEntityRepository(
    private val memberJpaRepository: MemberJpaRepository,
) : MemberRepository {
    @Transactional
    override fun save(member: MemberSignUp): UUID {
        return memberJpaRepository.save(member.toEntity()).id
    }
}
