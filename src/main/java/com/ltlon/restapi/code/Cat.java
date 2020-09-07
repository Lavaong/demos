package com.ltlon.restapi.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2020-03-03 16:43
 **/
/*@Data
@Component*/
public class Cat implements InitializingBean {
    String name;


    public Cat(String name){
        System.out.println("Cat 的 默认构造方法执行");
    }

    @PostConstruct
    public void afterInit(){
        System.out.println("Cat 的PostConstruct执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat 的 afterPropertiesSet执行");
    }
}
