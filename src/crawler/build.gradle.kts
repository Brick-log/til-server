plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":storage"))
    implementation(project(":protocol"))
    runtimeOnly("io.grpc:grpc-netty:1.53.0")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-protobuf:1.53.0")
    implementation("com.google.protobuf:protobuf-kotlin:3.22.0")
}
