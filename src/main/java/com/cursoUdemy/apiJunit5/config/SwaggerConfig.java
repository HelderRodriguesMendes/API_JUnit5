package com.cursoUdemy.apiJunit5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket config(){
        return new Docket(DocumentationType.OAS_30).select()
            .apis(RequestHandlerSelectors.basePackage("com.cursoUdemy"))
            .build().apiInfo(informAPI());
    }

    private ApiInfo informAPI(){
        return new ApiInfoBuilder().title("Gestão de Usuários")
            .description("Sistema de gestão de usuários")
            .version("1.0.0").build();
    }
}
