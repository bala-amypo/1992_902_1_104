package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // Server configuration (PORT / URL NOT CHANGED)
                .servers(List.of(
                        new Server().url("https://9007.408procr.amypo.ai/")
                ))
                // API Info
                .info(new Info()
                        .title("Employee Equipment Issuance Policy Checker API")
                        .version("1.0.0")
                        .description("API documentation")
                );
    }
}
