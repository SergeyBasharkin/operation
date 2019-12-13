package ru.alta.svd.operation.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alta.svd.operation.api.service.IdGenerator;

import java.util.UUID;

@Configuration
public class SvdOperationClientConfig {

    @Bean
    public IdGenerator generator(){
        return () -> UUID.randomUUID().toString();
    }
}
