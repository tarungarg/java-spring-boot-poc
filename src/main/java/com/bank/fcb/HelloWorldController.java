package com.bank.fcb;

import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloWorldController {


    private MessageSource messageSource;

    private HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @GetMapping("/hello")
    private String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-bean")
    private HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-bean/{id}/{name}")
    private HelloWorldBean helloWorldBeanPath(@PathVariable String id, @PathVariable String name){
        return new HelloWorldBean(String.format("Hello World %1$s  %2$s", name, id));
    }

    @GetMapping("/hello-bean-intl")
    private HelloWorldBean helloWorldBeanintl(){
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("spring.hello", null, "Default Message", locale);
        return new HelloWorldBean(message);
    }

    // Query param  ?version=1
    @GetMapping(value = "/hello-bean/param", params="version=1")
    private HelloWorldBean helloWorldBeanintlV1(){
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("spring.hello", null, "Default Message", locale);
        return new HelloWorldBean(message);
    }

    // X-API-VERSION in header
    @GetMapping(value = "/hello-bean/headers", headers="X-API-VERSION=2")
    private HelloWorldBean helloWorldBeanintlV2(){
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("spring.hello", null, "Default Message", locale);
        return new HelloWorldBean(message);
    }

    // Accept header
    @GetMapping(value = "/hello-bean/accept", produces = "application/vnd.company.app-v3+json")
    private HelloWorldBean helloWorldBeanintlV3(){
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("spring.hello", null, "Default Message", locale);
        return new HelloWorldBean(message);
    }
}
