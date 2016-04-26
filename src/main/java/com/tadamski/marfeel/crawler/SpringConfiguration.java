package com.tadamski.marfeel.crawler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CrawlerController.class)
public class SpringConfiguration {
}
