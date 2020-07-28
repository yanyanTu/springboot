package com.springcloud.eureka.ribbon.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableFeignClients
@EnableEurekaClient
@EnableHystrix // 开启Hystrix熔断处理
@SpringBootApplication
@EnableHystrixDashboard
public class DemoSpringcloudRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringcloudRibbonApplication.class, args);
    }

}
