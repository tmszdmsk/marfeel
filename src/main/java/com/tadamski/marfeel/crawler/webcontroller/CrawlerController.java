package com.tadamski.marfeel.crawler.webcontroller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class CrawlerController {

    @Autowired
    private CrawlerJobQueue jobQueue;

    @RequestMapping(path = "/jobs", method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = ACCEPTED)
    public void acceptCrawlerJobs(@RequestBody List<WebpageToCrawl> webpages) {
        webpages.forEach((it) -> jobQueue.push(CrawlerJob.webpage(it.hostname)));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public void handleValidationError() {
    }

    private static class WebpageToCrawl {
        @JsonProperty("url")
        String hostname;
    }
}
