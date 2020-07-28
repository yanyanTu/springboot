package com.example.springcloud.eureka.ribbon.controller;

import com.example.springcloud.eureka.ribbon.service.RestService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    private RestService restService;

    @GetMapping("/rest")
    public String restGet() {
        return restService.hi("http://eureka-client//hi");
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 获取eureka注册的服务器的信息
     *
     * @return
     */
    @GetMapping("/testRibbon")
    public String testRibbon() {
        ServiceInstance instance = loadBalancerClient.choose("eureka-client");
        return instance.getHost() + ":" + instance.getPort();
    }
}
