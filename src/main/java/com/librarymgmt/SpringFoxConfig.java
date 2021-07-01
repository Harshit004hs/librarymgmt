package com.librarymgmt;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

    /**
     * Api.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(this.custInfo());
    }

    /**
     * Cust info.
     *
     * @return the api info
     */
    public ApiInfo custInfo() {

        return new ApiInfo("Test", // Title
                "Spring Boot Services", // Description
                "1.0", // Version
                "TOS", // Terms of Service
                new Contact("Test", "Test.com", "test@test.com"), // Contact
                "Test license", // License
                "License", Collections.emptyList());
    }

    /**
     * Adds the resource handlers.
     *
     * @param registry the registry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}