package com.pragma.hogar360_microservice_house.commons.configurations.beans;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hogar360 API Houses")
                        .version("1.0.0")
                        .description("API to manage Houses Data"));
    }
}
