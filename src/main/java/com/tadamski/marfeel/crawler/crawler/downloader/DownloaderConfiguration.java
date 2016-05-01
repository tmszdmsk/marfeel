package com.tadamski.marfeel.crawler.crawler.downloader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tadamski.marfeel.crawler.crawler.WebpageDownloader;

@Configuration
public class DownloaderConfiguration {

    @Bean
    public WebpageDownloader downloader() {
        return new JsoupWebpageDownloader();
    }
}
