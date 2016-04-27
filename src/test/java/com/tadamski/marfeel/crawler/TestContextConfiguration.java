package com.tadamski.marfeel.crawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tadamski.marfeel.crawler.mocks.CrawlerJobQueueMock;

@Configuration
public class TestContextConfiguration {

    @Bean
    public CrawlerJobQueue crawlerJobQueue() {
        return new CrawlerJobQueueMock();
    }
}
