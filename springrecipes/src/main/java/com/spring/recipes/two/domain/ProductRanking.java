package com.spring.recipes.two.domain;

import com.spring.recipes.one.domain.Product;

public class ProductRanking {
    private Product bestSeller ;

    public Product getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Product bestSeller) {
        this.bestSeller = bestSeller;
    }
}
