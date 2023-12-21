package com.example.blog.storage.db.core.site

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SiteJpaRepository : JpaRepository<SiteEntity, UUID>, KotlinJdslJpqlExecutor
