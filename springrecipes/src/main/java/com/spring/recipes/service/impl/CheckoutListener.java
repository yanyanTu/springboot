package com.spring.recipes.service.impl;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Date;

public class CheckoutListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if( applicationEvent instanceof  CheckoutEvent){
            double amount = ((CheckoutEvent) applicationEvent).getAmount();
            Date time = ((CheckoutEvent) applicationEvent).getTime() ;
            System.out.println();
        }
    }
}
