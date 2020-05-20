package com.spring.recipes.one;

import com.spring.recipes.one.domain.Product;
import com.spring.recipes.one.service.impl.SequenceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
//        SequenceGenerator generator = (SequenceGenerator) context.getBean("SequenceGenerator");
//        generator.setInitial(10000);
//        generator.setPrefix("30");
//        generator.setSuffix("-");
//        System.out.println("---------------111---------------------");
//        System.out.println(generator.getSequence());
//        System.out.println(generator.getSequence());
//
//        SequenceGenerator initValueSequenceGenerator = (SequenceGenerator) context.getBean("initValueSequenceGenerator");
//        System.out.println("---------------222---------------------");
//        System.out.println(initValueSequenceGenerator.getSequence());
//        System.out.println(initValueSequenceGenerator.getSequence());
//
//        System.out.println("---------------333---------------------");
//        SequenceGenerator initSequenceGenerator = (SequenceGenerator)context.getBean("initSequenceGenerator");
//        System.out.println(initSequenceGenerator.getSequence());
//
//        System.out.println("---------------444----------------------");
//        SequenceGenerator pinitSequenceGenerator = (SequenceGenerator)context.getBean("pinitSequenceGenerator");
//        System.out.println(pinitSequenceGenerator.getSequence());
//
//        System.out.println("---------------555----------------------");
//        SequenceGenerator initListSequenceGenerator = (SequenceGenerator)context.getBean("initListSequenceGenerator");
//        System.out.println(initListSequenceGenerator.getList());
//
//        System.out.println("---------------666----------------------");
//        SequenceGenerator initMapSequenceGenerator = (SequenceGenerator)context.getBean("initMapSequenceGenerator");
//        System.out.println(initMapSequenceGenerator.getMapValue());
//
//        Product battery = (Product)context.getBean("battery");
//        System.out.println(battery);
//        Product disc = (Product) context.getBean("disc");
//        System.out.println(disc);
//
//        SequenceGenerator baseSequenceGenerator = (SequenceGenerator) context.getBean("baseSequenceGenerator");
//        System.out.println(baseSequenceGenerator.getPrefixGenerator());
//
//        SequenceGenerator initPschemaGenerator = (SequenceGenerator)context.getBean("initPschemaGenerator");
//        System.out.println(initPschemaGenerator.getPrefixGenerator());

        SequenceService sequenceService = (SequenceService)context.getBean("sequenceService");
        System.out.println(sequenceService.generate("IT"));
        System.out.println(sequenceService.generate("IT"));
    }
}
