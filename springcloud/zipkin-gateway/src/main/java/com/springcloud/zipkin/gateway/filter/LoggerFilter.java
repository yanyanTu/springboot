package com.springcloud.zipkin.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
public class LoggerFilter extends ZuulFilter {

    @Autowired
    private Tracer tracer ;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        // 请求链路中添加操作员信息
        tracer.addTag("operator", "test");
        // 日志中打印出请求的tracerId信息
        System.out.println("tracerId: " + tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
