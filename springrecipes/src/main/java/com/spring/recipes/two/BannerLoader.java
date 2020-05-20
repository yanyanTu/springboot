package com.spring.recipes.two;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

public class BannerLoader implements ResourceLoaderAware {

    private ResourceLoader resourceLoader  ;
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader ;
    }

    public void showBanner() throws IOException {
        Resource banner = resourceLoader.getResource("classpath:banner.txt");
        InputStream inputStream = banner.getInputStream() ;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while(true){
            String line = reader.readLine();
            if( line == null ){
                break;
            }
            System.out.println(line);
        }
        reader.close();
    }

    public static void main(String[] args ) throws IOException {
        BannerLoader loader = new BannerLoader();
        loader.showBanner();
    }
}
