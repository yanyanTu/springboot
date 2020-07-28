package com.example.springcloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoSpringcloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringcloudEurekaClientApplication.class, args);
    }

}
