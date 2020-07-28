package com.example.springcloud.eureka.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate ;

    public String hi(String url){
        return restTemplate.getForObject(url, String.class);
    }
}
