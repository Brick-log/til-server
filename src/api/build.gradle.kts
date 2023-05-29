import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    id("com.google.cloud.tools.jib")
}

dependencies {
    api(project(":protocol"))
    api("io.github.openfeign:feign-okhttp:${property("okHttpVersion")}")

    runtimeOnly("io.grpc:grpc-netty:${property("gRpcNettyVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${property("jsonWebTokenVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${property("jsonWebTokenVersion")}")

    implementation(project(":storage"))
    implementation("io.grpc:grpc-protobuf:${property("gRpcProtoBufferVersion")}")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jsoup:jsoup:${property("jSoupVersion")}")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("io.jsonwebtoken:jjwt-api:${property("jjwtApiVersion")}")
    implementation("io.grpc:grpc-kotlin-stub:${property("gRpcKotlinStubVersion")}")
    implementation("com.google.protobuf:protobuf-kotlin:${property("protoBufferKotlinVersion")}")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${property("openApiVersion")}")
}

tasks.named<BootJar>("bootJar") {
    archiveFileName.set("api.jar")
}

tasks.named<Jar>("jar") {
    enabled = false
}
