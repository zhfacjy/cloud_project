package com.hg.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 浩发 on 2020/7/12 16:28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.hg.test.feign")
public class TestServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestServiceApplication.class, args);
    }
}
