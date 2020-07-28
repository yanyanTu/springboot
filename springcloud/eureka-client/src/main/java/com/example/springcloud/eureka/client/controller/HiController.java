package com.example.springcloud.eureka.client.controller;

import com.example.springcloud.eureka.client.service.RestService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Value("${server.port}")
    private String port ;


    @GetMapping("/hi")
    public String hello(){
        return "Hi, I'm from port " + port ;
    }

}
