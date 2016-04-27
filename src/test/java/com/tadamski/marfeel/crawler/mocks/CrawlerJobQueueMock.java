package com.tadamski.marfeel.crawler.mocks;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import com.tadamski.marfeel.crawler.CrawlerJob;
import com.tadamski.marfeel.crawler.CrawlerJobQueue;

public class CrawlerJobQueueMock implements CrawlerJobQueue {
    private List<CrawlerJob> crawlerJobs = new ArrayList<>();

    @Override
    public void push(CrawlerJob job) {
        crawlerJobs.add(job);
    }

    public static class CrawlerJobQueueAssertions extends AbstractAssert<CrawlerJobQueueAssertions, CrawlerJobQueueMock> {

        protected CrawlerJobQueueAssertions(CrawlerJobQueueMock actual) {
            super(actual, CrawlerJobQueueAssertions.class);
        }

        public static CrawlerJobQueueAssertions assertThat(CrawlerJobQueueMock jobQueue) {
            return new CrawlerJobQueueAssertions(jobQueue);
        }

        public CrawlerJobQueueAssertions hasJob(CrawlerJob crawlerJob) {
            isNotNull();
            Assertions.assertThat(actual.crawlerJobs).contains(crawlerJob);
            return this;
        }
    }
}
