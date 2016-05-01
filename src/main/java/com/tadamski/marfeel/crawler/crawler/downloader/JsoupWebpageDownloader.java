package com.tadamski.marfeel.crawler.crawler.downloader;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.tadamski.marfeel.crawler.crawler.Webpage;
import com.tadamski.marfeel.crawler.crawler.WebpageDownloader;
import com.tadamski.marfeel.crawler.crawler.WebpageRequest;

class JsoupWebpageDownloader implements WebpageDownloader {

    @Override
    public Webpage download(WebpageRequest webpageRequest) {
        try {
            return Webpage.withContent(Jsoup.connect(webpageRequest.getUri().toString()).execute().body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
