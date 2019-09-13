package ktt.config;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfiguration {
    @Bean
    fun asyncExecutor(): Executor {
        return ThreadPoolTaskExecutor()
    }
}