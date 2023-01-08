package com.springweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：TODO
 *
 * @author admin
 * @date 2023-01-07 18:56
 **/
@RestController
@RequestMapping(value = "web")
public class HttpWebController {


    @GetMapping(value = "http")
    public String getString(){
        return "get Http success";
    }


}
