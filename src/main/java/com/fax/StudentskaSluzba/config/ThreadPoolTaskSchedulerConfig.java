package com.fax.StudentskaSluzba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolTaskSchedulerConfig {

//    ThreadPoolTaskScheduler is well suited for internal thread management,
//    as it delegates tasks to the ScheduledExecutorService and implements the TaskExecutor
//        interface – so that single instance of it is able to handle asynchronous potential
//    executions as well as the @Scheduled annotation.
//    Let's now define ThreadPoolTaskScheduler bean at ThreadPoolTaskSchedulerConfig:

//    The configured bean threadPoolTaskScheduler can execute tasks asynchronously based on the
//    configured pool size of 5.
//    Note that all ThreadPoolTaskScheduler related thread names will be prefixed with ThreadPoolTaskScheduler.

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}
