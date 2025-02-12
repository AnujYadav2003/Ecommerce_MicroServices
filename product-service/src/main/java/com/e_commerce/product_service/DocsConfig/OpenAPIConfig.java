package com.e_commerce.product_service.DocsConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceAPI(){
        return new OpenAPI()
                .info(new Info().title("Product Service API")
                        .description("This is the REST API for Product Service")
                        .version("v0.0.1").license(new License().name("Product 1.0")));
    }
}
