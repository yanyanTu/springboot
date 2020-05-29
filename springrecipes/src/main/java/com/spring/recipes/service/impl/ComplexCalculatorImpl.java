package com.spring.recipes.service.impl;

import com.spring.recipes.domain.Complex;
import com.spring.recipes.service.ComplexCalculator;

public class ComplexCalculatorImpl implements ComplexCalculator {
    @Override
    public Complex add(Complex a, Complex b) {
        Complex rst = new Complex(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary()
        );
        System.out.println(rst);
        return rst;
    }

    @Override
    public Complex sub(Complex a, Complex b) {
        Complex rst = new Complex(a.getReal() - b.getReal(), a.getImaginary() - b.getImaginary()
        );
        System.out.println(rst);
        return rst;
    }
}
