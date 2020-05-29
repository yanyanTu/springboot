package com.spring.recipes.aopaspectj.impl;

import com.spring.recipes.aopaspectj.UtilCalcultor;

public class UtilCalcultorImpl implements UtilCalcultor {
    public double kilogramToPound(double kilogram) {
        double pound = kilogram * 2.2 ;
        System.out.println("pound=" + pound +",kilogram=" + kilogram);
        return pound;
    }

    public double kilogramToMil(double kilometer) {
        double mile = kilometer * 0.62;
        System.out.println("mile=" + mile +",kilometer=" + kilometer);
        return mile;
    }
}
