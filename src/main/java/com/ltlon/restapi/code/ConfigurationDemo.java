package com.ltlon.restapi.code;

import com.ltlon.restapi.code.circulatedependency.Fish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2020-03-03 16:47
 **/
@Configuration
@ComponentScan("com.ltlon.restapi.code")
public class ConfigurationDemo {
    @Bean
    public Cat cat(){
        return new Cat("cat");
    }
}
