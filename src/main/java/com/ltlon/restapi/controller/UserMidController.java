package com.ltlon.restapi.controller;

import com.ltlon.restapi.bean.User;
import com.ltlon.restapi.dto.ResponseDTO;
import com.ltlon.restapi.dto.ResponseStatusCode;
import com.ltlon.restapi.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/mid")
@Slf4j
public class UserMidController {
    @PostMapping(value = "/paramCheck",consumes = "application/json",produces = "application/json")
    public ResponseDTO<UserDTO> paramCheck(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        log.info("------------------param check-----------------");
        if(bindingResult.hasErrors()){
            return new ResponseDTO(ResponseStatusCode.PARAMS_ERROR,handleError(bindingResult));
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        log.info(user.toString());
        //业务service
        return new ResponseDTO(ResponseStatusCode.SUCCESS,userDTO);
    }

    public String handleError(BindingResult bindingResult){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for(ObjectError objectError : allErrors){
            sb.append("[").append(objectError.getDefaultMessage()).append("]");
        }
        return sb.toString();
    }

}
