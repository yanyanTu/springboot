package com.spring.recipes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        // 构建应用上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");


    }
}
