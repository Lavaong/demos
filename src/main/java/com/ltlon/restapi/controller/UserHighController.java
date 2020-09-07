package com.ltlon.restapi.controller;

import com.ltlon.restapi.bean.User;
import com.ltlon.restapi.dto.ResponseDTO;
import com.ltlon.restapi.dto.ResponseStatusCode;
import com.ltlon.restapi.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: REST_API_DEMO
 * @description: consumes：提交的请求的内容类型（content-type)
 * produces：返回的内容类型
 * @author: LTL
 * @create: 2019-11-21 14:23
 **/
@RestController
@RequestMapping("/high")
@Slf4j
public class UserHighController {
    @PostMapping(value = "/paramCheck",consumes = "application/json",produces = "application/json")
    public ResponseDTO<UserDTO> paramCheck(@RequestBody @Valid UserDTO userDTO){
        log.info("------------------param check-----------------");
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        log.info(user.toString());
        //业务service
        return new ResponseDTO(ResponseStatusCode.SUCCESS,userDTO);
    }

    @PostMapping(value = "/users" )
    public ResponseDTO addUser(@Validated({UserDTO.Add.class}) @RequestBody UserDTO userDto){
        log.info("add user ------------");
        userDto.setUserId(1);
        return new ResponseDTO(ResponseStatusCode.SUCCESS,userDto);
    }

    @PutMapping(value = "/users" )
    public ResponseDTO updateUser(@Validated(UserDTO.Update.class) @RequestBody UserDTO userDto){
        log.info("update user ------------");
        return new ResponseDTO(ResponseStatusCode.SUCCESS,userDto);
    }

}
