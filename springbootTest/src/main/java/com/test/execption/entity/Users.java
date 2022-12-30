package com.test.execption.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 类描述：TODO
 *
 * @author admin
 * @date 2022-12-29 22:29
 **/
@Data
public class Users {

    @NotEmpty(message = "用户名不能为空")
    @Min(value = 3,message = "不能小于3个")
    public String userName;

    public String email;


}
