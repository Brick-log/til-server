plugins {
    kotlin("jvm")
}

dependencies {
    runtimeOnly("io.grpc:grpc-netty:${property("gRpcNettyVersion")}")

    implementation(project(":storage"))
    implementation(project(":protocol"))
    implementation("io.grpc:grpc-kotlin-stub:${property("gRpcKotlinStubVersion")}")
    implementation("org.jsoup:jsoup:${property("jSoupVersion")}")
    implementation("io.grpc:grpc-protobuf:${property("gRpcProtoBufferVersion")}")
    implementation("com.google.protobuf:protobuf-kotlin:${property("protoBufferKotlinVersion")}")
}
