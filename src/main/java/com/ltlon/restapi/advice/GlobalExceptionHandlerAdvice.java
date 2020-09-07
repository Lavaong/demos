package com.ltlon.restapi.advice;

import com.ltlon.restapi.dto.ResponseDTO;
import com.ltlon.restapi.dto.ResponseStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: REST_API_DEMO
 * @description: 全局Controller异常统一处理通知器
 * @author: LTL
 * @create: 2019-11-21 15:27
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO handleIllegalParamException(MethodArgumentNotValidException argException){
        String tips = "参数不合法";
        BindingResult bindingResult = argException.getBindingResult();
        if(argException.getBindingResult().hasErrors()){
            tips = handleError(bindingResult);
        }
        return new ResponseDTO(ResponseStatusCode.PARAMS_ERROR,tips);
    }

    private String handleError(BindingResult bindingResult){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        for(ObjectError objectError : allErrors){
            sb.append("[").append(objectError.getDefaultMessage()).append("]");
        }
        return sb.toString();
    }


    @ExceptionHandler(Exception.class)
    public ResponseDTO handleRuntimeException(Exception e){
        log.error(e.getLocalizedMessage());
        return new ResponseDTO(ResponseStatusCode.NET_ERROR);
    }
}
