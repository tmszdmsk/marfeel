package com.tadamski.marfeel.crawler;

import static com.tadamski.marfeel.crawler.CrawlerJob.webpage;
import static com.tadamski.marfeel.crawler.mocks.CrawlerJobQueueMock.CrawlerJobQueueAssertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tadamski.marfeel.crawler.mocks.CrawlerJobQueueMock;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebContextConfiguration.class, TestContextConfiguration.class})
public class CrawlerControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CrawlerJobQueueMock jobQueueMock;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldAcceptEmptyJsonArray() throws Exception {
        //when
        ResultActions result = mockMvc.perform(requestWith().content("[]"));

        //then
        result.andExpect(status().isAccepted());
    }

    @Test
    public void shouldPassUrlToQueue() throws Exception {
        //when
        mockMvc.perform(requestWith().content("[{\"url\": \"example.org\"}]"));

        //then
        assertThat(jobQueueMock).hasJob(webpage("example.org"));
    }

    private MockHttpServletRequestBuilder requestWith() {
        return post("/jobs").contentType(APPLICATION_JSON);
    }
}