package com.tadamski.marfeel.crawler.crawler.qualifier;

import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;

import com.tadamski.marfeel.crawler.crawler.QualificationResult;
import com.tadamski.marfeel.crawler.crawler.Webpage;
import com.tadamski.marfeel.crawler.crawler.WebpageQualifier;

class TitleTagQualifier implements WebpageQualifier {

    private List<String> qualifiedWords = Arrays.asList("news", "noticias");

    @Override
    public QualificationResult qualify(Webpage webpage) {
        String title = Jsoup.parse(webpage.getContent()).select("html > head > title").text();
        boolean containsAnyQualifiedWord = qualifiedWords.stream().anyMatch(
                (qualifiedWord) -> title.toLowerCase().contains(qualifiedWord)
        );
        if (containsAnyQualifiedWord) {
            return QualificationResult.qualified();
        } else {
            return QualificationResult.notQualified();
        }
    }
}
