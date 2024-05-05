package com.chandimal.auctionApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsncConfig {

    @Bean
    public Executor taskExecutor()
    {
        ThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(2);
        executor.setMaximumPoolSize(100);
        return executor;
    }
}
