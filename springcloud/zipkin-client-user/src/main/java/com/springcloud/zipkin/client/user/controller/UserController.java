package com.springcloud.zipkin.client.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hi")
    public String hi(){
        return "Hi, zipkinClient";
    }
}
