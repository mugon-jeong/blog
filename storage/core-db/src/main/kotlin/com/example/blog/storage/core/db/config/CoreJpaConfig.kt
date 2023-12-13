package com.example.blog.storage.core.db.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.example.blog.storage.core.db"])
@EnableJpaRepositories(basePackages = ["com.example.blog.storage.core.db"])
class CoreJpaConfig {
}