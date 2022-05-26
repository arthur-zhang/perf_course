package com.imdach.demo;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String requestURI = request.getRequestURI();
        System.out.println("prehandle: " + requestURI);

        String headerSign = request.getHeader("x-sign");
        System.out.println("x-sign: " + headerSign);

        String sign = DigestUtils.md5DigestAsHex(requestURI.getBytes());
        System.out.println("sign: " + sign);
//        if (!Objects.equals(sign, headerSign)) {
//            System.out.println("not equal");
//            throw new HttpUnauthorizedException();
//        }
        return true;
    }
}
