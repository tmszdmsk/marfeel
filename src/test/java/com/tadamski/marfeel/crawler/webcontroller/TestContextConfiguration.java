package com.tadamski.marfeel.crawler.webcontroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tadamski.marfeel.crawler.webcontroller.mocks.CrawlerJobQueueMock;

@Configuration
public class TestContextConfiguration {

    @Bean
    public CrawlerJobQueue crawlerJobQueue() {
        return new CrawlerJobQueueMock();
    }
}
