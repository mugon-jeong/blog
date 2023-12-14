package com.example.blog.core.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = ["com.example.blog"])
class CoreApiApplication

fun main(args: Array<String>) {
    runApplication<CoreApiApplication>(*args)
}
