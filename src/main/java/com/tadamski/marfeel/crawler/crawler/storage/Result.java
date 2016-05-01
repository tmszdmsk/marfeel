package com.tadamski.marfeel.crawler.crawler.storage;

import java.net.URI;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Result {

    @Id
    private URI uri;
    private Boolean qualified;

    Result() {
    }

    public Result(URI uri, Boolean qualified) {
        this.uri = uri;
        this.qualified = qualified;
    }

    public URI getUri() {
        return uri;
    }

    public Boolean isQualified() {
        return qualified;
    }
}
