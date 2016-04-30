package com.tadamski.marfeel.crawler.crawler;

public class QualificationResult {
    private boolean qualified;

    private QualificationResult(boolean qualified) {
        this.qualified = qualified;
    }

    public boolean isQualified() {
        return qualified;
    }

    public static QualificationResult notQualified() {
        return new QualificationResult(false);
    }

    public static QualificationResult qualified() {
        return new QualificationResult(true);
    }
}
