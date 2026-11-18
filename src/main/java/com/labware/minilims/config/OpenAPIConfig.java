package com.labware.minilims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini LIMS API")
                        .version("1.0")
                        .description(
                                "API REST para gestão do ciclo de vida de amostras laboratoriais e trilha de auditoria"));
    }
}