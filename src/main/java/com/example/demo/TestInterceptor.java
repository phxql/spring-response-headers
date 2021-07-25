package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@ControllerAdvice
class TestInterceptor implements HandlerInterceptor, ResponseBodyAdvice<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestInterceptor.class);

    private static final String HEADER_NAME = "x-date";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Is only respected on methods which are void

        LOGGER.info("postHandle({}, {}, {}, {})", request, response, handler, modelAndView);

        if (!response.isCommitted()) {
            response.addHeader(HEADER_NAME, Instant.now().toString());
        }
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // Is only respected on methods which are not void

        LOGGER.info("beforeBodyWrite({}, {}, {}, {}, {}, {})", body, returnType, selectedContentType, selectedConverterType, request, response);
        response.getHeaders().add(HEADER_NAME, Instant.now().toString());

        return body;
    }
}

