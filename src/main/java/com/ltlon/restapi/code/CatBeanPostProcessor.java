package com.ltlon.restapi.code;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2020-03-03 16:44
 **/
@Component
public class CatBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Cat){
            System.out.println("Cat postProcessorBefore 执行");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Cat){
            System.out.println("Cat postProcessorAfter 执行");
            Cat cat = (Cat) bean;
            cat.name = "chang to dog";
        }
        return bean;
    }
}
