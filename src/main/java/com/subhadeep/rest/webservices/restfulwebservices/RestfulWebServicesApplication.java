package com.subhadeep.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication{

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
//      when you use LocaleContextHolder, because of SessionLocaleResolver the headers passed in as Accept-language is skipped
//        In order to get that working we can use something called AcceptHeaderLocaleResolver
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }


//    Alternative to this is to add a property in application.properties file
//    Below code was written just to add the basename for the messages
//    @Bean
//    public ResourceBundleMessageSource bundleMessageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }

}
