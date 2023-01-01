package com.test.execption.exception;

/**
 * 类描述：自定义异常
 *
 * @author admin
 * @date 2023-01-01 16:13
 **/

public class TokenException extends Exception{
    public TokenException(String message) {
        super(message);
    }

}
