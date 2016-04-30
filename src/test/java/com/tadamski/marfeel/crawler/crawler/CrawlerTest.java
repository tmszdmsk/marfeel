package com.tadamski.marfeel.crawler.crawler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tadamski.marfeel.crawler.webcontroller.CrawlerJob;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerTest {

    private static final CrawlerJob CRAWLER_JOB_EXAMPLE = CrawlerJob.webpage("example.org");
    private static final WebpageRequest WEBPAGE_REQUEST_EXAMPLE = WebpageRequest.from(CRAWLER_JOB_EXAMPLE);
    private static final Webpage WEBPAGE_EXAMPLE = Webpage.withContent("example page");
    private static final QualificationResult QUALIFICATION_RESULT_EXAMPLE = QualificationResult.notQualified();

    @Mock
    private WebpageDownloader webpageDownloader;
    @Mock
    private WebpageQualifier webpageQualifier;
    @Mock
    private ResultsStorage resultsStorage;
    @InjectMocks
    private Crawler crawler;

    @Test
    public void shouldDownloadRequestedWebpage() {
        //when
        crawler.push(CRAWLER_JOB_EXAMPLE);

        //then
        verify(webpageDownloader).download(WEBPAGE_REQUEST_EXAMPLE);
    }

    @Test
    public void shouldQualifyDownloadedWebpage() {
        //given
        when(webpageDownloader.download(WEBPAGE_REQUEST_EXAMPLE)).thenReturn(WEBPAGE_EXAMPLE);

        //when
        crawler.push(CRAWLER_JOB_EXAMPLE);

        //then
        verify(webpageQualifier).qualify(WEBPAGE_EXAMPLE);
    }

    @Test
    public void shouldSaveQualificationResultInStorage() {
        //given
        when(webpageDownloader.download(WEBPAGE_REQUEST_EXAMPLE)).thenReturn(WEBPAGE_EXAMPLE);
        when(webpageQualifier.qualify(WEBPAGE_EXAMPLE)).thenReturn(QUALIFICATION_RESULT_EXAMPLE);

        //when
        crawler.push(CRAWLER_JOB_EXAMPLE);

        //then
        verify(resultsStorage).store(WEBPAGE_REQUEST_EXAMPLE, QUALIFICATION_RESULT_EXAMPLE);
    }
}
