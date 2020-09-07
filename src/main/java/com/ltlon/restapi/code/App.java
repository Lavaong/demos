package com.ltlon.restapi.code;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * @program: restapi
 * @description: 主类
 * @author: LTL
 * @create: 2020-02-17 16:37
 **/
public class App {
    public static void main(String[] args) throws Exception{
        //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ColorConfiguration.class);
        //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.ltlon.restapi.code.circulatedependency");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationDemo.class);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);
    }
}
