package com.tadamski.marfeel.crawler.crawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tadamski.marfeel.crawler.webcontroller.CrawlerJobQueue;

@Configuration
public class CrawlerConfiguration {

    @Bean
    public CrawlerJobQueue crawlerJobQueue(WebpageDownloader downloader, WebpageQualifier qualifier,
                                           ResultsStorage storage) {
        return new Crawler(downloader, qualifier, storage);
    }
}
