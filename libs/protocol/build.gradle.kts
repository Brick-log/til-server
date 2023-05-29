import com.google.protobuf.gradle.id

plugins {
    kotlin("jvm")
    id("com.google.protobuf")
}
dependencies {
    implementation(project(":utils"))
    implementation("io.grpc:grpc-protobuf:${property("gRpcProtoBufferVersion")}")
    implementation("io.grpc:grpc-kotlin-stub:${property("gRpcKotlinStubVersion")}")
    implementation("com.google.protobuf:protobuf-kotlin:${property("protoBufferKotlinVersion")}")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.22.0"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.53.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}
