package com.example.blog.storage.db.core.license

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LicenseJpaRepository : JpaRepository<LicenseEntity, UUID>, KotlinJdslJpqlExecutor
