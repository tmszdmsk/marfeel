package com.tadamski.marfeel.crawler.crawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;

import com.tadamski.marfeel.crawler.webcontroller.CrawlerJobQueue;

@Configuration
@EnableAsync
public class CrawlerConfiguration {

    @Bean
    public CrawlerJobQueue crawlerJobQueue(WebpageDownloader downloader, WebpageQualifier qualifier,
                                           ResultsStorage storage) {
        return new Crawler(downloader, qualifier, storage);
    }

    @Bean
    @Primary
    public CrawlerJobQueue asyncJobQueueDecorator(CrawlerJobQueue crawlerJobQueue) {
        return new AsyncCrawlerJobQueueDecorator(crawlerJobQueue);
    }
}
