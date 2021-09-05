package com.fubiye.edgar.domain.model.reader;


public class Paragraph implements Content<String> {
    private String content;

    @Override
    public String getContent() {
        return content;
    }

}
