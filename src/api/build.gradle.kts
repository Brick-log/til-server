plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":protocol"))
    api("io.github.openfeign:feign-okhttp:12.3")

    runtimeOnly("io.grpc:grpc-netty:1.53.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.7")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.7")

    implementation(project(":storage"))
    implementation("io.grpc:grpc-protobuf:1.53.0")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.jsonwebtoken:jjwt-api:0.10.5")
    implementation("com.google.protobuf:protobuf-kotlin:3.22.0")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.2")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    // security
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
}
