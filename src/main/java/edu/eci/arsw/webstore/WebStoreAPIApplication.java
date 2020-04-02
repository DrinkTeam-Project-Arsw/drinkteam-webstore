/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.webstore;

import com.google.common.base.Predicate;
import static com.google.common.base.Predicates.or;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author jmvillatei
 */
@SpringBootApplication
@Configuration
@EnableSwagger2
public class WebStoreAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebStoreAPIApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webStoreApiInfo())
                .select()
                .paths(webStorePaths())
                .apis(RequestHandlerSelectors.any())
                .build()
                .useDefaultResponseMessages(false);

    }

    private ApiInfo webStoreApiInfo() {

        return new ApiInfoBuilder()
                .title("Service IncogniTrade")
                .version("1.0")
                .license("Apache License Version 2.0")
                .build();

    }

    private Predicate<String> webStorePaths() {
        return or(
                regex("/api/v1.*")
        );
    }

}
