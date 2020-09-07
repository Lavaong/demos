package com.ltlon.restapi.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 自定义性别校验类
 * @params:
 * @return:
 * @author: LTL
 * @date: 2019/11/21 15:48
 */
public class SexValidate implements ConstraintValidator<Sex,String> {

    private Set<String> sexNum = new HashSet<>(2);
    
    /**
     * 校验表初始化
     */
    @Override
    public void initialize(Sex sex){
        for(String i : sex.sexNum()){
            sexNum.add(i);
        }
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return sexNum.contains(s);
    }
}
