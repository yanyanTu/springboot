package com.spring.recipes.service.impl;

import com.spring.recipes.service.StorageConfig;
import com.spring.recipes.two.domain.ShoppingCart;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.Date;

public class Cashier implements BeanNameAware, StorageConfig, MessageSourceAware, ApplicationEventPublisherAware {

    private String path ;

    private ApplicationEventPublisher applicationEventPublisher ;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher ;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }

    public void checkout(ShoppingCart shoppingCart){
        CheckoutEvent checkoutEvent = new CheckoutEvent(this, 0, new Date());
        applicationEventPublisher.publishEvent(checkoutEvent);
    }
}
