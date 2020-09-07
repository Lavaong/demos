package com.ltlon.restapi.dto;

import lombok.Data;

/**
 * @program: REST_API_DEMO
 * @description: 响应码和响应信息
 * @author: LTL
 * @create: 2019-11-21 14:54
 **/
public enum ResponseStatusCode {

    NET_ERROR("9999","请求异常！"),
    PARAMS_ERROR("0002","参数不合法！"),
    SUCCESS("0000","请求成功！"),
    FAILD("0001","无法访问,内部错误");

    private String code;

    private String msg;

    private ResponseStatusCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
