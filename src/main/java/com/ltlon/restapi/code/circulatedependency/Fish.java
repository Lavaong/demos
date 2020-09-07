package com.ltlon.restapi.code.circulatedependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: restapi
 * @description:
 * @author: LTL
 * @create: 2020-03-10 16:24
 **/
@Component
public class Fish {
    @Autowired
    Person person;


}
