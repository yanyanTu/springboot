package com.spring.recipes.service.impl;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

public class CheckoutEvent extends ApplicationEvent {
    private double amount ;

    private Date time ;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public CheckoutEvent(Object source, double amount, Date time) {
        super(source);
        this.amount = amount;
        this.time = time ;
    }
}
