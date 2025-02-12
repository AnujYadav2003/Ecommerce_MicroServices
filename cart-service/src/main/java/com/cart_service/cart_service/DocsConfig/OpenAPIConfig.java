package com.cart_service.cart_service.DocsConfig;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI cartServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Cart Service API")
                        .description("This is the REST API for Cart Service")
                        .version("v0.0.1").license(new License().name("Cart 1.0")));
    }

}
