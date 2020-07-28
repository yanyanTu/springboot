package com.springcloud.eureka.ribbon.client.controller;

import com.springcloud.eureka.ribbon.client.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    public FeignService feignService;

    @GetMapping("/feignHi")
    public String feignHi(){
        return feignService.sayHiFromEurekaClient();
    }
}
