package com.example.blog.storage.db.core.member

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberJpaRepository : JpaRepository<MemberEntity, UUID>
