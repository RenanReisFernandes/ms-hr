package com.renan.hrworker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.renan.hrworker"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("API de Funcionarios/ WORKER")
    			.description("API que gerencia dados dos funcionários")
    			.version("1.0.0")
    			.contact(new Contact("Renan Reis", "https://github.com/RenanReisFernandes", "https://www.linkedin.com/in/renan-dos-reis-fernandes-developer/"))
    			.build();
    }
}
