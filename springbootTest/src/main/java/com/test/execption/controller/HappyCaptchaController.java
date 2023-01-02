package com.test.execption.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.test.execption.exception.TokenCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类描述：验证码生成
 *
 * @author admin
 * @date 2023-01-02 16:00
 **/

@RestController
@RequestMapping(value = "happy-captcha")
public class HappyCaptchaController {

    /**
     * 验证码生成
     * @param request
     * @param response
     */
    @GetMapping(value = "generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response){
        HappyCaptcha.require(request,response).build().finish();

    }

    /**
     * 验证码验证
     * @param verifyCode
     * @param request
     * @return
     */
    @GetMapping(value = "verify")
    public String verify(String verifyCode, HttpServletRequest request){
        Boolean aBoolean = HappyCaptcha.verification(request,verifyCode,true);
        if (aBoolean){
            HappyCaptcha.remove(request);
            return "通过";
        }

        return "不通过";
    }


    /**
     * 验证码验证
     * @param verifyCode
     * @param request
     * @return
     */
    @GetMapping(value = "remove")
    public String remove(String verifyCode, HttpServletRequest request){
        Boolean aBoolean = HappyCaptcha.verification(request,verifyCode,true);
        //清除验证码
        HappyCaptcha.remove(request);
        if (aBoolean){
            HappyCaptcha.remove(request);
            return "通过";
        }

        return "不通过";
    }



}
