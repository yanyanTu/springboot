package com.spring.recipes.aopaspectj;

import com.spring.recipes.aopaspectj.impl.CounterImpl;
import com.spring.recipes.aopaspectj.impl.MaxCalculatorImpl;
import com.spring.recipes.aopaspectj.impl.MinCalculatorImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 实现一个接口引入多个实现类操作，避免实现类去继承多个接口，然后重写对应的实现方法
 */
@Aspect
public class CalculatorIntroduction {
    /**
     * @DeclareParents注解类型的value属性：引入的目标类；defaultImpl：用语实现这个新接口的实现类
     */
    @DeclareParents(value = "com.spring.recipes.aopaspectj.impl.ArithmeticCalculatorImpl", defaultImpl = MaxCalculatorImpl.class)
    public MaxCalculator maxCalculator ;

    @DeclareParents(value = "com.spring.recipes.aopaspectj.impl.ArithmeticCalculatorImpl", defaultImpl = MinCalculatorImpl.class)
    public MinCalculator minCalculator;

    @DeclareParents(value = "com.spring.recipes.aopaspectj.impl.*CalculatorImpl", defaultImpl = CounterImpl.class)
    public Counter counter;

    @After("execution(* com.spring.recipes.aopaspectj.impl.*CalculatorImpl.*(..)) && this(counter)")
    public void increaseCount(Counter counter){
        counter.increase();
    }
}
