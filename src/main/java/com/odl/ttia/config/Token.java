package com.odl.ttia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties("token")
@Configuration
@Data
public class Token {

    private String bearer;
    
}
