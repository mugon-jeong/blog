package com.example.blog.storage.db.core.blog

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BlogCommentJpaRepository : JpaRepository<BlogCommentEntity, UUID>
