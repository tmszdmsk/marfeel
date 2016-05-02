package com.tadamski.marfeel.crawler.crawler;

import org.springframework.scheduling.annotation.Async;

import com.tadamski.marfeel.crawler.webcontroller.CrawlerJob;
import com.tadamski.marfeel.crawler.webcontroller.CrawlerJobQueue;

class AsyncCrawlerJobQueueDecorator implements CrawlerJobQueue {

    private CrawlerJobQueue crawlerJobQueue;

    public AsyncCrawlerJobQueueDecorator(CrawlerJobQueue crawlerJobQueue) {
        this.crawlerJobQueue = crawlerJobQueue;
    }

    @Override
    @Async
    public void push(CrawlerJob job) {
        crawlerJobQueue.push(job);
    }
}
