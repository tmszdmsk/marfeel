package com.tadamski.marfeel.crawler.crawler.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadamski.marfeel.crawler.crawler.QualificationResult;
import com.tadamski.marfeel.crawler.crawler.ResultsStorage;
import com.tadamski.marfeel.crawler.crawler.WebpageRequest;

@Component
class SpringDataResultsStorage implements ResultsStorage {

    @Autowired
    private ResultsRepository repository;

    @Override
    public void store(WebpageRequest webpageRequest, QualificationResult qualificationResult) {
        Result result = new Result(webpageRequest.getUri(), qualificationResult.isQualified());
        repository.save(result);
    }
}
