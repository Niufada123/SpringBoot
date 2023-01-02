package com.test.execption.config;

import com.test.execption.exception.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类描述：TODO
 *
 * @author admin
 * @date 2023-01-01 17:09
 **/
@Configuration
public class IntercpetorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        		registry.addInterceptor(interceptor())
//				.addPathPatterns("/**")
//				;

    }

    @Bean
    public AuthInterceptor interceptor() {
        return new AuthInterceptor();
    }

}
