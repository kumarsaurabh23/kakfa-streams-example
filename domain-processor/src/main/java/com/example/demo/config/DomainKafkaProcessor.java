package com.example.demo.config;

import com.example.demo.domain.Domain;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Slf4j
public class DomainKafkaProcessor {

    @Bean
    public Function<KStream<String, Domain>,KStream<String, Domain>> domainProcessor() {
        return kstream -> kstream.filter((s, domain) -> {
            if(domain.isDead()) {
                log.info("Inactive domain {}", domain.getDomain());
            } else {
                log.info("Active domain {}", domain.getDomain());
            }
            return !domain.isDead();
        });
    }
}
