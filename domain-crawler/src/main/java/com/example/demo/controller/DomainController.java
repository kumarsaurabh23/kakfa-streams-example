package com.example.demo.controller;

import com.example.demo.service.DomainCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class DomainController {

    @Autowired
    private DomainCrawlerService domainCrawlerService;

    @GetMapping("lookup/{name}")
    public String lookup(@PathVariable String name) {
        domainCrawlerService.crawl(name);
        return "Domain crawler has scrapped your data";
    }
}
