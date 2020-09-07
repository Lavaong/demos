package com.ltlon.restapi.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @description: Sex自定义注解
 * @params:
 * @return:
 * @author: LTL
 * @date: 2019/11/21 15:57
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SexValidate.class)
@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER,TYPE_USE})
public @interface Sex {

    String message() default "性别不合法";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    /***
     * 默认男,女 两种类型
     */
    String[] sexNum() default {"1","2"};
}
