dependencies {
    implementation(project(":core:core-enum"))
    // 기본 어노테이션
    implementation("org.springframework:spring-context")
    // transaction
    implementation("org.springframework:spring-tx")
    // for page, sort
    // https://mvnrepository.com/artifact/org.springframework.data/spring-data-commons
    implementation("org.springframework.data:spring-data-commons")
}
