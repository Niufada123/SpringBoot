package com.test.execption.enums;

import lombok.Data;

/**
 * 类描述：枚举类
 *
 * @author admin
 * @date 2022-12-29 22:48
 **/
public enum StatusCodeEnums {

    SUCCESS("200","请求成功"),
    Fail("202","请求成功");
    private String Code;
    private String Msg;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    StatusCodeEnums(String code, String msg){
        this.Code = code;
        this.Msg = msg;
    }

}
