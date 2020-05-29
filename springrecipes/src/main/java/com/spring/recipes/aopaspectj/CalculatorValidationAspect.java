package com.spring.recipes.aopaspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

@Aspect
public class CalculatorValidationAspect implements Ordered {

    @Before("execution(* *.*(double, double))")
    public void validateBefore(JoinPoint joinPoint){
        for(Object arg : joinPoint.getArgs()){
            validate((Double) arg);
        }
    }

    private void validate(double arg) {
        if( arg < 0){
            throw new IllegalArgumentException("Positive numbers only");
        }
    }

    /**
     * aspect优先权设置，值越小，优先权越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
