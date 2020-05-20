package com.spring.recipes.two.domain;

import com.spring.recipes.one.domain.Product;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Date;

public class Cashier {
    private String name ;
    private String path ;
    private BufferedWriter writer ;

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @PostConstruct
    public void openFile()throws IOException{
        File logFile = new File(path, name +".txt");
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true)));
    }

    public void checkout(ShoppingCart cart) throws IOException{
        double total = 0;
        for(Product product : cart.getItems()){
            total += product.getPrice() ;
        }
        writer.write(new Date() + "\t" + total + "\r\n");
        writer.flush();
    }

    @PreDestroy
    public void closeFile() throws IOException{
        writer.close();
    }

}
