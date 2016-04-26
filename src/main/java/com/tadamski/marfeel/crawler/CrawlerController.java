package com.tadamski.marfeel.crawler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {

    @RequestMapping(path = "/jobs", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void acceptCrawlerJob() {

    }
}
