package com.ltlon.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: REST_API_DEMO
 * @description:
 * @author: LTL
 * @create: 2019-11-21 11:09
 **/

@RestController
@RequestMapping("/api")
public class DemoController {


    @GetMapping("/v1.0/demo")
    public String getDemo(){
        return "hello ,rest api!";
    }



}
