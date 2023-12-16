package com.example.blog.storage.db.core.post

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PostJpaRepository : JpaRepository<PostEntity, UUID>
