package com.spring.recipes.domain;

import com.spring.recipes.one.domain.Product;

import java.util.Date;

public class ProductRanking {
    private Product bestSeller ;
    private Date fromDate ;
    private Date toDate ;

    public Product getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Product bestSeller) {
        this.bestSeller = bestSeller;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "ProductRanking{" +
                "bestSeller=" + bestSeller +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
