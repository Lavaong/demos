package com.ltlon.restapi.code;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author litlb
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@Import({Red.class,})
public @interface EnableColor {
}
