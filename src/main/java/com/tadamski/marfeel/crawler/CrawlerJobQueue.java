package com.tadamski.marfeel.crawler;

public interface CrawlerJobQueue {
    void push(CrawlerJob job);
}
