package com.tadamski.marfeel.crawler.crawler;

import com.tadamski.marfeel.crawler.webcontroller.CrawlerJob;
import com.tadamski.marfeel.crawler.webcontroller.CrawlerJobQueue;

public class Crawler implements CrawlerJobQueue {

    private WebpageDownloader webpageDownloader;
    private WebpageQualifier webpageQualifier;
    private ResultsStorage resultsStorage;

    public Crawler(WebpageDownloader pageDownloader, WebpageQualifier webpageQualifier, ResultsStorage resultsStorage) {
        this.webpageDownloader = pageDownloader;
        this.webpageQualifier = webpageQualifier;
        this.resultsStorage = resultsStorage;
    }

    @Override
    public void push(CrawlerJob job) {
        Webpage page = webpageDownloader.download(WebpageRequest.from(job));
        QualificationResult qualificationResult = webpageQualifier.qualify(page);
        resultsStorage.store(qualificationResult);
    }
}
