package vip.efactory.eflow.config;

import org.flowable.spring.boot.cmmn.Cmmn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class MyConfiguration {

//    @Process
//    @Bean
//    public TaskExecutor processTaskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
//
//    @Cmmn
//    @Bean
//    public TaskExecutor cmmnTaskExecutor() {
//        return new SyncTaskExecutor();
//    }
}
