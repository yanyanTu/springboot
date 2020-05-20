package com.spring.recipes.config;


import com.google.common.collect.Maps;
import com.spring.recipes.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class XssConfig {

    public FilterRegistrationBean xssFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParam = Maps.newHashMap();
        initParam.put("isIncludeRichText", "true");
        return filterRegistrationBean ;

    }
}
