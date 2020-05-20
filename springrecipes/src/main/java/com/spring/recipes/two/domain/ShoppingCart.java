package com.spring.recipes.two.domain;

import com.spring.recipes.one.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items = new ArrayList<>() ;

    public void addItem(Product item){
        items.add(item);
    }

    public List<Product> getItems (){
        return items ;
    }
}
