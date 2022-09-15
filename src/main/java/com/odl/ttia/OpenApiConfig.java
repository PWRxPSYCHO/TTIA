package com.odl.ttia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class OpenApiConfig {
    // http://localhost:8080/swagger-ui.html#/
    // https://springdoc.org/

    @Bean
    public OpenAPI api() { 
        return new OpenAPI()  
          .info(new Info().title("Twitter Threat Intel API")
          .description("Malicious Link Detection through #opendir hastag on Twitter")
          .version("v0.0.1")
          .license(new License().name("Apache 2.0").url("https://orbital-data-labs.io")));                            
    }
}
