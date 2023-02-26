package com.tenmm.crawler

import io.grpc.examples.helloworld.GreeterGrpcKt
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import org.springframework.stereotype.Component

@Component
class HelloService : GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest): HelloReply {
        return HelloReply.newBuilder()
            .setMessage(request.name)
            .build()
    }
}
