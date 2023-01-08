package com.springweb.intercept;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http强制跳转https
 */
@Configuration
public class HttpToHttps {

    // https的端口
    @Value("${server.port}")
    private int sslPort;

    // http的端口
    @Value("${http-port}")
    private int httpPort;

    @Bean
    public TomcatServletWebServerFactory servletContainerFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                //设置安全性约束
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                //设置约束条件
                SecurityCollection collection = new SecurityCollection();
                //拦截所有请求
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        //遇到http进行转发
        connector.setScheme("http");

        //true： http使用http, https使用https;
        //false： http重定向到https;
        connector.setSecure(false);

        // 设置http端口
        connector.setPort(httpPort);

        //重定向端口号(非SSL到SSL)
        connector.setRedirectPort(sslPort);

        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}