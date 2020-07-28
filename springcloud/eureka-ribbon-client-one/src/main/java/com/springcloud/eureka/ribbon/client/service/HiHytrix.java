package com.springcloud.eureka.ribbon.client.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 熔断处理，调用异常时容错处理实现类
 */
//@Component
//public class HiHytrix implements FeignService {
//@Override
//public String sayHiFromEurekaClient() {
//        return "Hi, Hytrix failed";
//        }
//        }

@Component
public class HiHytrix implements FallbackFactory<FeignService> {

    @Override
    public FeignService create(Throwable cause) {
        return new FeignService() {
            @Override
            public String sayHiFromEurekaClient() {
                return "Hi, Hytrix failed";
            }
        };
    }
}
