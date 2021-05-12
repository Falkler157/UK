package com.runner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import service.DBService;
import service.RestLogCollector;

@Configuration
@ComponentScan({"service"})
public class ApplicationConfig {

    @Bean
    @Primary
    public DBService createIDBService() {
        return new DBService();
    }

    @Bean
    public RestLogCollector createIRestLogCollector() {
        return new RestLogCollector();
    }

    @Bean
    public Executor createExecutor() {
        Executor executor = new Executor(createIRestLogCollector(), createIDBService());
        executor.run();
        return executor;
    }
}
