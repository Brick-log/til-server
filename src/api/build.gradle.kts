plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":protocol"))
    implementation(project(":storage"))
    runtimeOnly("io.grpc:grpc-netty:1.53.0")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-protobuf:1.53.0")
    implementation("com.google.protobuf:protobuf-kotlin:3.22.0")
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webflux-ui
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.2")
    implementation("org.jetbrains:annotations:23.0.0")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
}
