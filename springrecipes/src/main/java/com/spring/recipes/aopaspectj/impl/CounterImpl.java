package com.spring.recipes.aopaspectj.impl;

import com.spring.recipes.aopaspectj.Counter;

public class CounterImpl implements Counter {
    private  int count ;

    @Override
    public void increase() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
