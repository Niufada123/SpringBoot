package com.test.execption.exception;

import com.test.execption.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 类描述：统一异常处理
 *
 * @author admin
 * @date 2022-12-29 22:31
 **/
@RestControllerAdvice
public class CheckHandler {

    @ExceptionHandler(ArithmeticException.class)
    public Result resultExecption(Exception e) {
        return Result.getSuccess().code("301").msg("数学异常").build();
    }





}
