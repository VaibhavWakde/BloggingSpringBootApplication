package com.codewithdurgesh.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKeys() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(sf())
                        .build()
        );
    }


    private List<SecurityReference> sf() {
        AuthorizationScope scopes = new AuthorizationScope("global","accessEverything");

        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo(
                "Blogging Application",
                "This project is developed by Vaibhav Wakde",
                "1.0","Terms of service",
                new Contact("Vaibhav","htt","vaibhavwakde98@gmail.com"),"License of APIs", "API license URL", Collections.emptyList());
    }
}
