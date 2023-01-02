package com.test.execption.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.wf.captcha.*;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类描述：EasyCaptcha 验证码工具
 *
 * @author admin
 * @date 2023-01-02 17:47
 **/

@RestController
@RequestMapping(value = "captcha")
public class EasyCaptchaController {

    /**
     * 生成字符串验证码
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "generatorStr")
    public void generator(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil.out(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 生成数字验证码
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "generatorNum")
    public void generatorNum(HttpServletRequest request, HttpServletResponse response) {
        try {
            ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(200, 250);
            arithmeticCaptcha.setLen(2);
            CaptchaUtil.out(arithmeticCaptcha, request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成中文验证码
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "generatorChinese")
    public void generatorChinese(HttpServletRequest request, HttpServletResponse response) {
        try {
            ChineseCaptcha chineseCaptcha = new ChineseCaptcha(200, 250);
            chineseCaptcha.setLen(2);
            CaptchaUtil.out(chineseCaptcha, request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成中文动态验证码
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "generatorChineseGif")
    public void generatorChineseGif(HttpServletRequest request, HttpServletResponse response) {
        try {
            ChineseGifCaptcha chineseGifCaptcha = new ChineseGifCaptcha(200, 250);
            chineseGifCaptcha.setLen(2);
            CaptchaUtil.out(chineseGifCaptcha, request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成字符动态验证码
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "generatorGif")
    public void generatorGif(HttpServletRequest request, HttpServletResponse response) {
        try {
            GifCaptcha captcha = new GifCaptcha(200, 250);
            captcha.setLen(2);
            CaptchaUtil.out(captcha, request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成字符动态验证码
     * 这个为默认验证码生成器
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "generatorSpec")
    public void generatorSpec(HttpServletRequest request, HttpServletResponse response) {
        try {
            SpecCaptcha captcha = new SpecCaptcha(200, 250);
            captcha.setLen(2);
            CaptchaUtil.out(captcha, request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 验证码校验，并且验证通过后清除，下次不允许再用
     *
     * @param verifyCode
     * @param request
     * @return
     */
    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request) {

        Boolean aBoolean = CaptchaUtil.ver(verifyCode, request);
        if (aBoolean) {
            CaptchaUtil.clear(request); //清除验证码
            return "通过";
        }
        return "不通过";
    }




}
