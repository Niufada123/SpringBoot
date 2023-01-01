package com.test.execption.controller;

import com.test.execption.entity.Result;
import com.test.execption.entity.Users;
import com.test.execption.exception.TokenCheck;
import com.test.execption.utils.JwtUtil;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 类描述：接口模拟
 *
 * @author admin
 * @date 2022-12-29 22:27
 **/

@RestController
public class TestController {


    /**
     * 返回成功封装
     * @return
     */
    @GetMapping("/success")
    public Result test(){
        return Result.getSuccess().build();
    }

    /**
     * 返回失败封装
     * @param users
     * @return
     */
    @GetMapping("/fail")
    public Result fail(@RequestBody  Users users){
        return Result.getFail().build();
    }


    /**
     * 返回数字异常
     * @return
     */
    @GetMapping("/exception")
    public Result poException(){
        int aa = 1/0;
        return Result.getFail().build();
    }

    /**
     * 对象注解校验
     * @param user
     * @return
     */
    @GetMapping("/user")
    public Result poException(@RequestBody @Valid Users user){
        user.getUserName();
        return Result.getFail().build();
    }

    /**
     * 自定义异常测试
     * @return
     */
    @GetMapping("/token")
    public Result gettoken(@RequestParam(value = "token") String token){
        String token1 = JwtUtil.createToken(token);
        return Result.getSuccess().data(token1).build();
    }
    @TokenCheck
    @GetMapping("/parse")
    public Result parsetoken( @RequestParam(value = "token") String token){
        return Result.getSuccess().build();
    }

}
