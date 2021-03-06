package com.spring.recipes.service.impl;

import com.spring.recipes.one.domain.Product;

import java.beans.PropertyEditorSupport;

public class ProductEditor extends PropertyEditorSupport {
    public String getAsText(){
        Product product = (Product) getValue();
        return product.getClass().getName()+"," + product.getName()+"," + product.getPrice();
    }

    public void setAsText(String text){
        String[] parts = text.split(",");
        try {
            Product product = (Product)Class.forName(parts[0]).newInstance() ;
            product.setName(parts[1]);
            product.setPrice(Double.parseDouble(parts[2]));
            setValue(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
