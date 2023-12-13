tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":core:core-domain"))
    runtimeOnly(project(":storage:core-db"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}