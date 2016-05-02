package com.tadamski.marfeel.crawler;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.jayway.awaitility.Awaitility.waitAtMost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.jayway.awaitility.Duration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        Application.ApplicationConfiguration.class
})
@WebAppConfiguration
public class ApplicationIT {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void shouldStartTheWholeApplication() {
        assertThat(context).isNotNull();
    }

    @Test
    public void shouldQualify() throws Exception {
        //given
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(200)));

        //when
        ResultActions result = mvc.perform(
                post("/jobs").content("[{\"url\": \"localhost:" + wireMockRule.port() + "\"}]").contentType(MediaType
                        .APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isAccepted());
        waitAtMost(Duration.ONE_SECOND).until(() ->
                JdbcTestUtils.countRowsInTable(jdbcTemplate, "RESULT") > 0
        );
    }
}