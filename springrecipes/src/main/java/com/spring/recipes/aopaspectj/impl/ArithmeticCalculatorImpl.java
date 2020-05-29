package com.spring.recipes.aopaspectj.impl;

import com.spring.recipes.aopaspectj.ArithmeticCalculator;
import com.spring.recipes.aopaspectj.LoggingRequired;


public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

    @LoggingRequired()
    public double add(double a, double b) {
        double rst  = a + b ;
        System.out.println(a + "+" + b + "=" + rst);
        return rst;
    }

    @LoggingRequired
    public double sub(double a, double b) {
        double rst = a - b ;
        System.out.println(a + "-" + b + "=" + rst);
        return rst;
    }

    @LoggingRequired
    public double mul(double a, double b) {
        double rst = a * b ;
        System.out.println(a + "*" + b + "=" + rst);
        return rst;
    }

    @LoggingRequired
    public double div(double a, double b) {
        if( b ==0){
            throw new IllegalArgumentException("Division by zero");
        }
        double rst = a / b ;
        System.out.println( a +"/" + b + "=" + rst);
        return rst;
    }


}
