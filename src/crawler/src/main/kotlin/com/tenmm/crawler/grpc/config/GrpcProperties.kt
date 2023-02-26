package com.tenmm.crawler.grpc.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("grpc")
data class GrpcProperties(
    val port: Int,
)
