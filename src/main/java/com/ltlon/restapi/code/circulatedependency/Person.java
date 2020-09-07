package com.ltlon.restapi.code.circulatedependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2020-03-10 16:25
 **/
@Component
public class Person {

    @Autowired
    Fish fish;
}
