package com.test.execption.exception;

import com.test.execption.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 类描述：统一异常处理
 *
 * @author admin
 * @date 2022-12-29 22:31
 **/
@RestControllerAdvice
public class CheckHandler {


    /**
     * 数字异常
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    public Result resultExecption(Exception e) {
        return Result.getSuccess().code("301").msg("数学异常").build();
    }


    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(TokenException.class)
    public Result tokenExecption(Exception e) {
        return Result.getSuccess().code("301").msg("token").build();
    }


}
