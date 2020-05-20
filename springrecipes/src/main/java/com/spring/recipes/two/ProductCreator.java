package com.spring.recipes.two;

import com.spring.recipes.one.domain.Product;

public class ProductCreator {
    public static Product createProduce(String productId){
        if( "aaa".equals(productId)){
            return new Product("AAA", 2.5);
        }
        else if("cdrw".equals(productId)){
            return new Product("CD-RW", 1.5);
        }
        throw new IllegalArgumentException("unknow exception");
    }
}
