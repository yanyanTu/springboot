package com.springcloud.eureka.ribbon.client.service;

import com.springcloud.eureka.ribbon.client.config.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * fallback与fallbackFactory一样
 */
//@FeignClient(value = "eureka-client", configuration = FeignConfig.class, fallback = HiHytrix.class)
@FeignClient(value = "eureka-client", configuration = FeignConfig.class, fallbackFactory = HiHytrix.class)
public interface FeignService {

    @GetMapping("/hi")
    String sayHiFromEurekaClient();
}
