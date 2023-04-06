package com.tenmm.tilserver.common.adapter.outbound.grpc

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("grpc")
data class GrpcProperties(
    val crawlingServer: GrpcServerInfo,
)

data class GrpcServerInfo(
    val address: String,
    val port: Int,
)
