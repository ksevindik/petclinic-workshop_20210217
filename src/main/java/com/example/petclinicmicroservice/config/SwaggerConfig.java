package com.example.petclinicmicroservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(security = @SecurityRequirement(name="HttpBasicAuth"))
@SecurityScheme(name = "HttpBasicAuth",type = SecuritySchemeType.HTTP,scheme = "basic")
@Configuration
public class SwaggerConfig {
}
