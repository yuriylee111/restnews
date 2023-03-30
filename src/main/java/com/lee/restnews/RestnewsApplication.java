package com.lee.restnews;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "RestNews API",
                description = "Spring Boot 3.0 documentation",
                version = "1.0",
                contact = @Contact(
                        name = "Yuriy Lee",
                        email = "yl@mail.co",
                        url = "www.yl.org"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "License URL"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "External Documentation",
                url = "https://github.com/yuriylee111/restnews"
        )
)
public class RestnewsApplication {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(RestnewsApplication.class, args);
    }

}
