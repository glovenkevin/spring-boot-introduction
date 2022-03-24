package com.smu.tes.demo.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info().title("Demo Wings API")
                    .description("This is my presentation demo application for showing how Spring Boot Works")
                    .version("v0.0.1")
                    .contact(Contact()
                        .email("glovenkevincch@gmail.com")
                        .name("Kevin Christian Chandra")
                    )
            )
    }

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("demo-public")
            .pathsToMatch("/**")
            .build()
    }

}