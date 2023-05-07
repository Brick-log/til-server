package com.tenmm.tilserver.common.config.rest

import com.tenmm.tilserver.security.domain.UserAuthInfo
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "BearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    `in` = SecuritySchemeIn.HEADER,
    paramName = "Authorization"
)
class SwaggerConfig {

    init {
        val ignoreTypes = setOf<Class<*>>(UserAuthInfo::class.java).toTypedArray()
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(*ignoreTypes)
    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        val securityRequirement = SecurityRequirement().addList("BearerAuth")

        return OpenAPI()
            .components(Components())
            .info(
                Info().title("Brick-Log")
            )
            .addSecurityItem(securityRequirement)
    }
}
