package com.spring.recipes.aopaspectj.impl;

import com.spring.recipes.aopaspectj.MaxCalculator;

public class MaxCalculatorImpl implements MaxCalculator {
    @Override
    public double max(double a, double b) {
        double rst = (a >= b) ? a : b ;
        System.out.println("max("+a+","+b+")="+ rst);
        return rst;
    }
}
