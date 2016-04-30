package com.tadamski.marfeel.crawler.crawler;

public interface ResultsStorage {
    void store(WebpageRequest webpageRequest, QualificationResult result);
}
