package com.tenmm.tilserver.common.config.rest

import com.tenmm.tilserver.common.security.annotation.OptionalUserArgumentResolver
import com.tenmm.tilserver.common.security.annotation.RequiredUserArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer

@Configuration
class WebFluxConfig(
    private val requiredUserArgumentResolver: RequiredUserArgumentResolver,
    private val optionalUserArgumentResolver: OptionalUserArgumentResolver,
) : WebFluxConfigurer {
    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(requiredUserArgumentResolver, optionalUserArgumentResolver)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("*")
    }
}
