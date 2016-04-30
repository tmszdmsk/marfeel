package com.tadamski.marfeel.crawler.crawler;

public class Webpage {
    private String content;

    private Webpage(String content) {
        this.content = content;
    }

    public static Webpage withContent(String content) {
        return new Webpage(content);
    }

    public String getContent() {
        return content;
    }
}
