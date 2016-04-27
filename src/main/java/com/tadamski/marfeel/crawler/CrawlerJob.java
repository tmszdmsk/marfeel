package com.tadamski.marfeel.crawler;

import java.util.Objects;

public class CrawlerJob {

    private String url;

    private CrawlerJob(String url) {
        this.url = url;
    }

    public static CrawlerJob webpage(String url) {
        return new CrawlerJob(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrawlerJob that = (CrawlerJob) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "CrawlerJob{" +
                "url='" + url + '\'' +
                '}';
    }
}
