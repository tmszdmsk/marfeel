package com.tadamski.marfeel.crawler.crawler;

import java.net.URI;
import java.util.Objects;

import com.tadamski.marfeel.crawler.webcontroller.CrawlerJob;

public class WebpageRequest {

    private URI uri;

    public WebpageRequest(URI uri) {
        this.uri = uri;
    }

    public static WebpageRequest from(CrawlerJob job) {
        return new WebpageRequest(job.getUri());
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebpageRequest that = (WebpageRequest) o;
        return Objects.equals(uri, that.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    @Override
    public String toString() {
        return "WebpageRequest{" +
                "uri='" + uri + '\'' +
                '}';
    }
}
