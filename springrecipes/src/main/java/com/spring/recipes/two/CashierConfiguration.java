package com.spring.recipes.two;

import com.spring.recipes.filter.XssFilter;
import com.spring.recipes.two.domain.Cashier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(XssFilter.class)
public class CashierConfiguration {

    /**
     * 自定义bean，且指定初始化和析构方法
     * @return
     */
    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier josh(){
        Cashier cashier = new Cashier();
        cashier.setName("iii");
        cashier.setPath("E:/cashier");
        return  cashier;
    }
}
