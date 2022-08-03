package com.example.demo.service;

import com.example.demo.domain.Domain;
import com.example.demo.domain.DomainList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DomainCrawlerService {

    @Autowired
    private KafkaTemplate<String, Domain> kafkaTemplate;
    private final String TOPIC_NAME = "web-domains";

    public void crawl(String name) {
        Mono<DomainList> domainListMono = WebClient.create()
                                                    .get()
                                                    .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name)
                                                    .accept(MediaType.APPLICATION_JSON)
                                                    .retrieve()
                                                    .bodyToMono(DomainList.class);

        domainListMono.subscribe(domainList -> {
            domainList.getDomains().forEach(domain -> {
                kafkaTemplate.send(TOPIC_NAME, domain);
                log.info("Domain message published {}", domain);
            });
        });
    }
}
