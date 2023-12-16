tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":core:core-enum"))
    implementation(project(":support:logging"))
    implementation(project(":support:monitoring"))
    implementation(project(":storage:db-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
}
