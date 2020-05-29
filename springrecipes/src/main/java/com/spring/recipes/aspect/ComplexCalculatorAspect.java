package com.spring.recipes.aspect;

import com.spring.recipes.domain.Complex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ComplexCalculatorAspect {
    private Map<String, Complex> cache ;

    public ComplexCalculatorAspect(){
        cache = Collections.synchronizedMap(new HashMap<String, Complex>());
    }

    /**
     * call 切入是AspectJ的方法，而不是Spring AOP的方法，故需要在xml文件中对齐进行引入
     * @param proceedingJoinPoint
     * @param a
     * @param b
     * @return
     * @throws Throwable
     */
    @Around("call(public Complex.new(int, int)) && args(a, b)")
    public Object cachedAround(ProceedingJoinPoint proceedingJoinPoint, int a, int b) throws Throwable {
        String key = a +","+ b;
        Complex complex = cache.get(key);
        if( complex==null){
            System.out.println("Cache is not " + key +" complex object");
            complex = (Complex) proceedingJoinPoint.proceed();
            cache.put(key, complex);
        }else{
            System.out.println("Cache has " + key +" complex object");
        }
        return complex;
    }
}
