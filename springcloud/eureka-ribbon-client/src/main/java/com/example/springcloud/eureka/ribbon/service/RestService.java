package com.example.springcloud.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorHandler")
    public String hi(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 远程请求接口异常时处理方法，其中，参数需和正常处理的参数保持一致
     * @return
     */
    public String errorHandler(String name){
        return "Hi restTemplate failed!" + name ;
    }
}
