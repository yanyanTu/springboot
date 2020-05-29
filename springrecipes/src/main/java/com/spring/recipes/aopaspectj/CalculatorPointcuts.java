package com.spring.recipes.aopaspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CalculatorPointcuts {
    @Pointcut("within(ArithmeticCalculator+)")
    public void arithmeticOperation(){}

    @Pointcut("within(UtilCalcultor+)")
    public void unitOperation(){}

    @Pointcut("arithmeticOperation()||unitOperation()")
    public void loggingOperation(){}
}
