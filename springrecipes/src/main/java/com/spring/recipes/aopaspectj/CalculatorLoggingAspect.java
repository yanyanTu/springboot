package com.spring.recipes.aopaspectj;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Aspect
@Order(1) // 标明aspect的优先级级别，值越小优先级越高
public class CalculatorLoggingAspect {

    private Log log = LogFactory.getLog(this.getClass());

    @Before("execution(* *.*(..))")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("before ---->>>  The method " + joinPoint.getSignature().getName() +"() begins with " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(* *.*(..))" )
    public void logEnd(JoinPoint joinPoint){
        System.out.println("afterReturning ---->>>  The method " + joinPoint.getSignature().getName() +"() end");
    }

    /**
     * 方法执行完后返回执行结果后，将返回结果作为入参传入该方法
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "execution(* *.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        System.out.println("afterReturning  joinpoint ---->>>  The method " + joinPoint.getSignature().getName() +"() end with " + result);
    }

    /**
     * 执行方法出现异常时进入该方法中进行操作
     * @param joinPoint
     */
    @AfterThrowing("execution(* *.*(..))")
    public void logAfterThrowing(JoinPoint joinPoint){
        System.out.println("an exception has been thrown in " + joinPoint.getSignature().getName() +"()");
    }

    @Around("execution(* *.*(..))")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("round ---->>>  the method " + proceedingJoinPoint.getSignature().getName()+"() begins with " +
                Arrays.toString(proceedingJoinPoint.getArgs()));
        Object rst = proceedingJoinPoint.proceed();
        System.out.println("round ---->>>  the method " + proceedingJoinPoint.getSignature().getName()+"() ends with " + rst);
        return rst ;
    }

}
