package com.spring.recipes.service.impl;

import com.spring.recipes.service.StorageConfig;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;

import java.io.File;

public class PathCheckingBeanPostProcessor implements BeanPostProcessor, PriorityOrdered {
    private int order ;

    public Object postProcessBeforeInitialization(Object bean, String beanName){
        if( bean instanceof StorageConfig){
            String path = ((StorageConfig) bean).getPath();
            File file = new File(path);
            if( !file.exists()){
                file.mkdirs();
            }
        }
        return bean ;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)
    {
        return bean ;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order){
        this.order = order;
    }

}
