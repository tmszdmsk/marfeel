package com.tadamski.marfeel.crawler.crawler;

import java.util.Objects;

import com.tadamski.marfeel.crawler.webcontroller.CrawlerJob;

public class WebpageRequest {

    private String url;

    public WebpageRequest(String url) {
        this.url = url;
    }

    public static WebpageRequest from(CrawlerJob job) {
        return new WebpageRequest(job.getUrl());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebpageRequest that = (WebpageRequest) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "WebpageRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
