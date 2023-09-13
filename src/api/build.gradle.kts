import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    id("com.google.cloud.tools.jib")
}

dependencies {
    implementation(project(":storage"))
    implementation(project(":utils"))

    api("io.github.openfeign:feign-okhttp:${property("okHttpVersion")}")

    runtimeOnly("io.grpc:grpc-netty:${property("gRpcNettyVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${property("jsonWebTokenVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${property("jsonWebTokenVersion")}")
    implementation("io.jsonwebtoken:jjwt-api:${property("jjwtApiVersion")}")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jsoup:jsoup:${property("jSoupVersion")}")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${property("openApiVersion")}")
}

tasks.named<BootJar>("bootJar") {
    archiveFileName.set("api.jar")
}

tasks.named<Jar>("jar") {
    enabled = false
}
