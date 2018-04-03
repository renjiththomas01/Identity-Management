package com.springboot.identitymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication can be used instead of the first three annotations below.
@Configuration
@EnableAutoConfiguration
@ComponentScan
//@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class IdentityManager {

    public static void main(String[] args) {
        SpringApplication.run(IdentityManager.class, args);
    }
}
