package com.ak.banking.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Configuration
public class BankInterceptor implements HandlerInterceptor {
    // add logger
    public static final Logger LOGGER = LoggerFactory.getLogger(BankInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Log the details
        LOGGER.info("Request URL: {}", request.getRequestURL());
        LOGGER.info("Request Method: {}", request.getMethod());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Log the details
        LOGGER.info("post method is getting called !!!");
        LOGGER.info("Response Status: {}", Optional.of(response.getStatus()));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Log the details
        LOGGER.info("Request and Response is completed !!!");
    }
}
