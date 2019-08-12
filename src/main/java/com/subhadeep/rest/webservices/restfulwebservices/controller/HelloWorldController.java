package com.subhadeep.rest.webservices.restfulwebservices.controller;

import com.subhadeep.rest.webservices.restfulwebservices.model.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // If you use @Bean ResourceBundleMessageSource in SpringBoot Main Application class then you have to use the below variable type
    // However, messages base name if its set in application.properties then the below variable can be of both
    // MessageSource or ResourceBundleMessageSource type
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping(method= RequestMethod.GET, path="/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }

    @RequestMapping(method=RequestMethod.GET, path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean!");
    }

    @RequestMapping(method=RequestMethod.GET, path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(name);
    }

    // Internationalization
//    @RequestMapping(method=RequestMethod.GET, path="/hello-world-internationalized")
//    public String helloWorldInternationalized(@RequestHeader(value="Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("good.morning.message", null, locale);
//    }

//    Simplified Internationalization
    @RequestMapping(method=RequestMethod.GET, path="/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
