package com.tadamski.marfeel.crawler.webcontroller;

import java.net.URI;
import java.util.Objects;

public class CrawlerJob {

    private URI uri;

    private CrawlerJob(String uri) {
        this.uri = URI.create(uri);
    }

    public static CrawlerJob webpage(String hostname) {
        return new CrawlerJob("http://" + hostname);
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrawlerJob that = (CrawlerJob) o;
        return Objects.equals(uri, that.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    @Override
    public String toString() {
        return "CrawlerJob{" +
                "uri='" + uri + '\'' +
                '}';
    }
}
