package com.test.execption.exception;

import com.test.execption.entity.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 类描述：
 *  实体类注解出现异常返回
 * @author admin
 * @date 2022-12-29 23:11
 **/
@ControllerAdvice
public class ValidHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        //拿到报错列表，循环拼接，返回前端
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            stringBuilder.append(fieldError.getDefaultMessage());
        }
        return new ResponseEntity(Result.builder().code("102").msg(stringBuilder.toString()).build() ,
                HttpStatus.OK);
    }

}
