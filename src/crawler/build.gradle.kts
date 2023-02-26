plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":storage"))
    implementation(project(":protocol"))
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-protobuf:1.53.0")
    implementation("com.google.protobuf:protobuf-kotlin:3.22.0")
    implementation("net.devh:grpc-spring-boot-starter:2.14.0.RELEASE")
}
