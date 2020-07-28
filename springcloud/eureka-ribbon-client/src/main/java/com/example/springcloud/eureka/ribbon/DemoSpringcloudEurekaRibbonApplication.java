package com.example.springcloud.eureka.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication // 开启springboot基本功能
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker  // 需与EnableHystrix同步开启使用
@EnableHystrixDashboard
public class DemoSpringcloudEurekaRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringcloudEurekaRibbonApplication.class, args);
    }

}
