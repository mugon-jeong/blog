allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    compileOnly(project(":core:core-domain"))
    compileOnly(project(":core:core-enum"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")

    // kotlin jdsl
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:3.2.0")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:3.2.0")

    // https://github.com/f4b6a3/ulid-creator?tab=readme-ov-file
    implementation("com.github.f4b6a3:ulid-creator:5.2.2")
}
