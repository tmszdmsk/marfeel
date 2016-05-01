package com.tadamski.marfeel.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import com.tadamski.marfeel.crawler.crawler.CrawlerConfiguration;
import com.tadamski.marfeel.crawler.crawler.downloader.DownloaderConfiguration;
import com.tadamski.marfeel.crawler.crawler.qualifier.QualifierConfiguration;
import com.tadamski.marfeel.crawler.crawler.storage.StorageContextConfiguration;
import com.tadamski.marfeel.crawler.webcontroller.WebContextConfiguration;

public class Application {
    public static void main(String... args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

    @Configuration
    @Import({
            WebContextConfiguration.class,
            QualifierConfiguration.class,
            StorageContextConfiguration.class,
            DownloaderConfiguration.class,
            CrawlerConfiguration.class
    })
    static class ApplicationConfiguration {
        @Bean
        public UndertowEmbeddedServletContainerFactory servletContainerFactory() {
            UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory(9090);
            return factory;
        }

        @Bean
        public DispatcherServlet dispatcherServlet() {
            return new DispatcherServlet();
        }

    }

}


