package com.subhadeep.rest.webservices.restfulwebservices.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact("Subhadeep Biswas", "https://github.com/subhadeep-bis", "subhadeepbiswas500@gmail.com");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Awesome API Title", "Awesome Api Documentation",
            "1.0", "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0");
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

}
