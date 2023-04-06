package com.tenmm.tilserver.common.adapter.outbound.grpc

import com.tenmm.tilserver.protocol.CrawlerServiceGrpcKt
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfig {

    private fun buildGrpcMessageChannel(grpcServerInfo: GrpcServerInfo): ManagedChannel {
        return ManagedChannelBuilder.forAddress(grpcServerInfo.address, grpcServerInfo.port).usePlaintext().build()
    }

    @Bean
    fun crawlerServiceCoroutineStub(grpcProperties: GrpcProperties): CrawlerServiceGrpcKt.CrawlerServiceCoroutineStub {
        return CrawlerServiceGrpcKt.CrawlerServiceCoroutineStub(buildGrpcMessageChannel(grpcProperties.crawlingServer))
    }
}
