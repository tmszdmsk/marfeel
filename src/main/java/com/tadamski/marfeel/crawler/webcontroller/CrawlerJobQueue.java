package com.tadamski.marfeel.crawler.webcontroller;

public interface CrawlerJobQueue {
    void push(CrawlerJob job);
}
