package com.spring.recipes.aopaspectj.impl;

import com.spring.recipes.aopaspectj.MinCalculator;

public class MinCalculatorImpl implements MinCalculator {
    @Override
    public double min(double a, double b) {
        double rst = (a <= b) ? a : b ;
        System.out.println("min("+a+","+b+")="+ rst);
        return rst;
    }
}
