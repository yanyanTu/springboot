package com.spring.recipes.one.service.impl;

import com.spring.recipes.one.service.IPrefixGenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePrefixGeneratorImpl implements IPrefixGenerator {
    private DateFormat dateFormat;

    public void setPattern(String pattern){
        this.dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public String getPrefix() {
        return this.dateFormat.format(new Date());
    }
}
