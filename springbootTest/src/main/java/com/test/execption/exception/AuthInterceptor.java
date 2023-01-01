package com.test.execption.exception;

import com.test.execption.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 类描述：TODO
 *
 * @author admin
 * @date 2023-01-01 17:07
 **/
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截器进入");

        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)){
            throw new TokenException("token 为空");
//			return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(TokenCheck.class)){
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            if (annotation.required()){
                // 校验token
                try {
                    JwtUtil.parseToken(token);
                    return true;
                }catch (Exception e){
                    throw new TokenException("token 异常");
                }

            }
        }


        return true;
    }
}
