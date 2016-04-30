package com.tadamski.marfeel.crawler.crawler.qualifier;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tadamski.marfeel.crawler.crawler.QualificationResult;
import com.tadamski.marfeel.crawler.crawler.Webpage;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TitleTagQualifierTest {

    private TitleTagQualifier sut = new TitleTagQualifier();

    @Test
    public void shouldNotQualifyEmptyPage() {
        //when
        QualificationResult result = sut.qualify(emptyPage());

        //then
        assertThat(result.isQualified()).isFalse();
    }

    @Test
    @Parameters({
            "News",
            "news",
            "abcdnewsef",
            "noticias",
            "AAAAAAAaaaaaaa NoTiCiAs!!!!!!!!!!!!"
    })
    public void shouldQualifyPageWithTitleTag(String titleTag) throws IOException {
        //when
        QualificationResult result = sut.qualify(pageWithTitleTag(titleTag));

        //then
        assertThat(result.isQualified()).isTrue();
    }

    @Test
    public void shouldNotQualifyPageWithNotMatchingTitleTag() throws IOException {
        //when
        QualificationResult result = sut.qualify(pageWithTitleTag("not matching title tag"));

        //then
        assertThat(result.isQualified()).isFalse();
    }

    @Test
    public void shouldNotQualifyPageWithMisplacedTitleTag() throws IOException {
        //when
        QualificationResult qualificationResult = sut.qualify(pageWith("misplaced_title_tag"));

        //then
        assertThat(qualificationResult.isQualified()).isFalse();
    }

    @Test
    public void shouldNotQualifyPageWithDoubleTitleTag() throws IOException {
        //when
        QualificationResult qualificationResult = sut.qualify(pageWith("doubled_title_tag"));

        //then
        assertThat(qualificationResult.isQualified()).isFalse();
    }

    @Test
    public void shouldNotQualifyPageWithNoTitleTag() throws IOException {
        //when
        QualificationResult qualificationResult = sut.qualify(pageWith("no_title_tag"));

        //then
        assertThat(qualificationResult.isQualified()).isFalse();
    }

    private Webpage pageWith(String s) throws IOException {
        return Webpage.withContent(getTestPageContent(s + ".html"));
    }

    private Webpage pageWithTitleTag(String titleTag) throws IOException {
        return Webpage.withContent(
                getTestPageContent("simple.html").replace("{{title}}", titleTag)
        );
    }

    private String getTestPageContent(String fileName) throws IOException {
        return IOUtils.toString(
                this.getClass().getClassLoader().getResource("crawler/qualifier/test/pages/" + fileName)
        );
    }

    private Webpage emptyPage() {
        return Webpage.withContent("");
    }
}