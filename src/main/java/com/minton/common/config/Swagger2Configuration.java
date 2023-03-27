package com.minton.common.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Configuration
public class Swagger2Configuration {
    @Bean
    public OpenAPI msOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("MS API")
                        .description("manage-system")
                        .version("v0.0.1"));
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("SpringShop Wiki Documentation")
//                        .url("https://springshop.wiki.github.org/docs"));
    }
}