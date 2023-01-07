package com.test.execption.exception;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
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



    @ExceptionHandler(KaptchaException.class)
    public String kcaptchaException(KaptchaException e){
        if (e instanceof KaptchaTimeoutException){
            return "超时";
        }else if (e instanceof KaptchaIncorrectException){
            return "不正确";
        }else if (e instanceof KaptchaNotFoundException){
            return "没找到";
        }else {
            return "反正错了";
        }
    }

}
