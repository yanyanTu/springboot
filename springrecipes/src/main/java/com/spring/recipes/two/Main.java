package com.spring.recipes.two;

import com.spring.recipes.one.domain.Product;
import com.spring.recipes.two.domain.Cashier;
import com.spring.recipes.two.domain.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-two.xml");

        Product aaa = (Product) context.getBean("aaa");
        Product cdrw = (Product)context.getBean("cdrw");
        Product dvrw = (Product)context.getBean("dvrw");

        ShoppingCart shoppingCart = (ShoppingCart) context.getBean("shoppingCart");
        shoppingCart.addItem(aaa);
        shoppingCart.addItem(cdrw);
        System.out.println(shoppingCart.getItems());

        ShoppingCart cart1  = (ShoppingCart)context.getBean("shoppingCart");
        cart1.addItem(dvrw);
        System.out.println(cart1.getItems());

        Cashier cashier1 = (Cashier) context.getBean("cashier1");
        cashier1.checkout(shoppingCart);
    }
}
