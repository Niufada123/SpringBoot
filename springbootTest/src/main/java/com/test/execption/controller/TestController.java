package com.test.execption.controller;

import com.test.execption.entity.Result;
import com.test.execption.entity.Users;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 类描述：TODO
 *
 * @author admin
 * @date 2022-12-29 22:27
 **/

@RestController
public class TestController {


    @GetMapping("/success")
    public Result test(){
        return Result.getSuccess().build();
    }


    @GetMapping("/fail")
    public Result fail(@RequestBody  Users users){
        return Result.getFail().build();
    }


    @GetMapping("/exception")
    public Result poException(){
        int aa = 1/0;
        return Result.getFail().build();
    }

    @GetMapping("/user")
    public Result poException(@RequestBody @Valid Users user){
        user.getUserName();
        return Result.getFail().build();
    }


}
