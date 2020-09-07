package com.ltlon.restapi.bean;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @program: REST_API_DEMO
 * @description:
 * @author: LTL
 * @create: 2019-11-21 14:40
 **/
@Data
public class User {

    /***用户id***/
    private Integer userId;
    /***用户姓名***/
    private String userName;

    /***用户性别***/
    private String sex;
    /***用户年龄***/
    private int age;
    private String phone;
    private String email;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

}
