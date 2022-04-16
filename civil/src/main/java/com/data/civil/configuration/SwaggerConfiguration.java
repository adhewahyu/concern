package com.data.civil.configuration;

import com.data.civil.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String BASE_PACKAGE = "com.data.civil.controller";

    private static final Tag API_PERSON = new Tag(Constants.API_TAGS_PERSON, "Provide APIs for Person Inquiry");

    @Value("${swagger.host}")
    private String swaggerHost;

    private Docket getBaseDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build()
                .apiInfo(getDefaultApiInfo());
    }

    private ApiInfo getDefaultApiInfo() {
        return new ApiInfo("Civil Data Docs", "API sandbox for Civil Data service. Only for development purpose and API discovery.", "v1.4", "http://swagger.io/terms/",
                new Contact("Adhe Wahyu Ardanto", "", "adhe.wahyu.ardanto@gmail.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }

    @Bean
    public Docket docket() {
        return getBaseDocket()
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build().groupName("All APIs");
    }

    @Bean
    public Docket personDocket() {
        return getBaseDocket().groupName("Inquiry Person Recipe")
                .select()
                .paths(PathSelectors.regex("/person/v1/inquiry-by-id-number"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .tags(API_PERSON);
    }


}
