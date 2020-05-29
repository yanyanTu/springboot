package com.spring.recipes.aopaspectj;

import com.spring.recipes.domain.Complex;
import com.spring.recipes.one.domain.Product;
import com.spring.recipes.service.ComplexCalculator;
import com.spring.recipes.two.domain.Cashier;
import com.spring.recipes.two.domain.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-three.xml");

        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) context.getBean("arithmeticCalculator");
        arithmeticCalculator.add(1,2);
        arithmeticCalculator.sub(3,4);
        arithmeticCalculator.mul(5, 6);
        arithmeticCalculator.div(7, 8);


        UtilCalcultor utilCalcultor = (UtilCalcultor) context.getBean("utilCalcultor");
        utilCalcultor.kilogramToMil(5);
        utilCalcultor.kilogramToPound(10);

        ArithmeticCalculator arithmeticCalculator1 = (ArithmeticCalculator) context.getBean("arithmeticCalculator");
        MaxCalculator maxCalculator = (MaxCalculator) arithmeticCalculator1;
        maxCalculator.max(1, 3);

        MinCalculator minCalculator = (MinCalculator)arithmeticCalculator1;
        minCalculator.min(2, 10);

        Counter counter = (Counter)arithmeticCalculator;
        System.out.println("arithmeticCalculator: "  + counter.getCount());

//        Counter counter1 = (Counter)utilCalcultor ;
//        System.out.println("utilCalcultor: " + counter1.getCount());

        Counter counter2 = (Counter)maxCalculator ;
        System.out.println("maxCalculator: " + counter2.getCount());

        Counter counter3 = (Counter)minCalculator ;
        System.out.println("minCalculator: " + counter3.getCount());

        ComplexCalculator complexCalculator = (com.spring.recipes.service.ComplexCalculator) context.getBean("ComplexCalculator");
        complexCalculator.add(new Complex(1, 2), new Complex(3, 4));
        complexCalculator.sub(new Complex(10, 23), new Complex(1, 2));
    }
}
