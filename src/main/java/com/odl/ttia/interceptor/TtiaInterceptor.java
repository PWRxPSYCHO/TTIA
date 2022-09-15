package com.odl.ttia.interceptor;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import ch.qos.logback.classic.Logger;

public class TtiaInterceptor implements AsyncHandlerInterceptor {

    private TtiaInterceptor() {

    }

    public static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TtiaInterceptor.class);

}
