package com.tadamski.marfeel.crawler.crawler.storage;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tadamski.marfeel.crawler.crawler.QualificationResult;
import com.tadamski.marfeel.crawler.crawler.WebpageRequest;
import com.tadamski.marfeel.crawler.webcontroller.CrawlerJob;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StorageContextConfiguration.class})
public class SpringDataResultsStorageTest {

    public static final String EXAMPLE_HOSTNAME = "google.com";
    public static final URI EXAMPLE_URI = URI.create("http://google.com");

    @Autowired
    private SpringDataResultsStorage sut;
    @Autowired
    private ResultsRepository resultsRepository;

    @Before
    public void clearData() {
        resultsRepository.deleteAll();
    }

    @Test
    public void shouldSaveDataInRepositiory() {
        //given
        long numberOfResultsBefore = resultsRepository.count();

        //when
        sut.store(request(EXAMPLE_HOSTNAME), QualificationResult.qualified());

        //then
        assertThat(resultsRepository.count()).isGreaterThan(numberOfResultsBefore);
    }

    @Test
    public void shouldSaveQualificationDetails() {
        //when
        sut.store(request(EXAMPLE_HOSTNAME), QualificationResult.qualified());
        Result result = resultsRepository.findOne(EXAMPLE_URI);

        //then
        assertThat(result.isQualified()).isTrue();
        assertThat(result.getUri()).isEqualTo(EXAMPLE_URI);
    }

    @Test
    public void shouldOverrideOldData() {
        //given
        sut.store(request(EXAMPLE_HOSTNAME), QualificationResult.qualified());

        //when
        sut.store(request(EXAMPLE_HOSTNAME), QualificationResult.notQualified());

        //then
        assertThat(resultsRepository.findOne(EXAMPLE_URI).isQualified()).isFalse();

    }

    private WebpageRequest request(String hostname) {
        return WebpageRequest.from(CrawlerJob.webpage(hostname));
    }
}