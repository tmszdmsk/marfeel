package com.tadamski.marfeel.crawler.crawler.qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tadamski.marfeel.crawler.crawler.WebpageQualifier;

@Configuration
public class QualifierConfiguration {

    @Bean
    public WebpageQualifier webpageQualifier() {
        return new TitleTagQualifier();
    }
}
