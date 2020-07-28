package com.springcloud.zipkin.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin.storage.mysql.MySQLStorage;

import javax.sql.DataSource;

@Configuration
public class ZipkinConfig {

    @Bean
    public MySQLStorage mysqlStorage(DataSource dataSource){
        return MySQLStorage.builder().datasource(dataSource).executor(Runnable::run).build();
    }
}
