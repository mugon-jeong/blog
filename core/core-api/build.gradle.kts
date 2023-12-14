tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":core:core-domain"))
    implementation(project(":support:logging"))
    implementation(project(":support:monitoring"))
    runtimeOnly(project(":storage:db-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}
