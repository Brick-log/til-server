package com.tenmm.tilserver.common.security.annotation

import com.tenmm.tilserver.common.domain.UnAuthorizedException
import com.tenmm.tilserver.security.application.inbound.ResolveTokenUseCase
import com.tenmm.tilserver.security.domain.SecurityTokenType
import com.tenmm.tilserver.security.domain.UserAuthInfo
import io.jsonwebtoken.ExpiredJwtException
import mu.KotlinLogging
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

private val logger = KotlinLogging.logger {}

@Component
class RequiredUserArgumentResolver(
    private val resolveTokenUseCase: ResolveTokenUseCase,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasMethodAnnotation(RequiredAuthentication::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange,
    ): Mono<Any> {
        try {
            val tokenWithBearer = exchange.request.headers["Authorization"]?.get(0) ?: throw UnAuthorizedException()

            val token = tokenWithBearer.substringAfter("Bearer ")
            val userIdentifier = resolveTokenUseCase.resolveToken(token, SecurityTokenType.ACCESS)

            return UserAuthInfo(userIdentifier).toMono()
        } catch (e: ExpiredJwtException) {
            throw e
        } catch (e: Exception) {
            logger.error(e) { "Parse Authorization fail" }
            throw UnAuthorizedException()
        }
    }
}
