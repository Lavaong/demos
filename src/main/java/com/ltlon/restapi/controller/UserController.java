package com.ltlon.restapi.controller;

import com.ltlon.restapi.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @program: REST_API_DEMO
 * @description: 用户相关API
 * @author: LTL
 * @create: 2019-11-21 11:12
 **/
@RestController
@RequestMapping("/api")
public class UserController {

    /**
     * @description: 查询所有用户
     * @params: []
     * @return: java.lang.String
     * @author: LTL
     * @date: 2019/11/21 11:28
     */
    @GetMapping(value = "/users",produces = "application/json")
    public String getUsers(){
        return "get Users!";
    }
    /**
     * @description: 通过ID查询单个用户
     * @params: [userId]
     * @return: java.lang.String
     * @author: LTL
     * @date: 2019/11/21 11:28
     */
    @GetMapping(value = "/users/{userId}",produces = "application/json")
    public String getUserById(@RequestParam("userId")String userId){
        return "get user by Id:"+userId;
    }
    /**
     * @description: 添加用户
     * @params: [UserDTO userDTO]
     * @return: java.lang.String
     * @author: LTL
     * @date: 2019/11/21 11:28
     */
    @PostMapping(value = "/users",consumes = "application/json",produces = "application/jdon")
    public String creatUser(@RequestBody UserDTO userDTO){
        return "create users!";
    }

    /**
     * @description: 更新用户
     * @params: [UserDTO userDTO]
     * @return: java.lang.String
     * @author: LTL
     * @date: 2019/11/21 11:30
     */
    @PutMapping(value = "/users",produces = "application/json",consumes = "application/json")
    public String updateUser(@RequestBody UserDTO userDTO){
        return "update users!";
    }
    /**
     * @description:
     * @params: [userId]
     * @return: java.lang.String
     * @author: LTL
     * @date: 2019/11/21 11:37
     */ 
    @DeleteMapping(value = "/users/{userId}",consumes = "application/json",produces = "application/json")
    public String deleteUserById(@RequestParam("userId")String userId){
        return "delete user by Id"+userId  ;
    }
    @DeleteMapping(value = "/users",produces = "application/json")
    public String deleteUsers(){
        return "delete all Usrs";
    }
}
