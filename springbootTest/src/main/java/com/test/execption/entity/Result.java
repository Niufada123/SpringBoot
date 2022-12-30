package com.test.execption.entity;

import com.test.execption.enums.StatusCodeEnums;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 类描述：TODO
 *
 * @author admin
 * @date 2022-12-29 22:39
 **/
@Data
@Builder
public class Result<T> implements Serializable {

    public String code;
    public String msg;
    public T data;

    /**
     * 返回成功状态
     * @return
     */
    public static Result.ResultBuilder getSuccess(){
        return Result.builder().code(StatusCodeEnums.SUCCESS.getCode()).msg(StatusCodeEnums.SUCCESS.getMsg()).data(null);
    }

    /**
     * 返回失败状态
     * @return
     */
    public static Result.ResultBuilder getFail(){
        return Result.builder().code(StatusCodeEnums.Fail.getCode()).msg(StatusCodeEnums.Fail.getMsg()).data(null);
    }
}
