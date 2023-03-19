package com.tenmm.crawler.grpc.config

import io.grpc.BindableService
import io.grpc.ServerBuilder
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.stereotype.Component

@Component
class GrpcServer(
    bindableServices: List<BindableService>,
    grpcProperties: GrpcProperties,
) {
    private val server = ServerBuilder.forPort(grpcProperties.port)
        .addServices(bindableServices.map { it.bindService() })
        .build()

    @PostConstruct
    fun setUp() {
        print("GrpcServer.setUp: ${server.services}")
        server.start()
    }

    @PreDestroy
    fun sutDown() {
        print("GrpcServer.sutDown")
        server.shutdown()
    }
}
