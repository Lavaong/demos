package com.ltlon.restapi.dto;

import lombok.Data;

/**
 * @program: REST_API_DEMO
 * @description: 接口统一返回对象
 * @author: LTL
 * @create: 2019-11-21 14:48
 **/
@Data
public class ResponseDTO<T> {
    /**响应状态码**/
    private String code;

    /**响应描述信息**/
    private String msg;
    /**响应数据 **/
    private T data;

    public ResponseDTO(ResponseStatusCode responseStatusCode){
        this.code = responseStatusCode.getCode();
        this.msg = responseStatusCode.getMsg();
    }

    public ResponseDTO(ResponseStatusCode responseStatusCode,T data){
        this(responseStatusCode);
        this.data = data;
    }
}
