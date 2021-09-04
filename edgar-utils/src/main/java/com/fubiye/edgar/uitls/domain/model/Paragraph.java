package com.fubiye.edgar.uitls.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paragraph implements Content<String> {
    private String content;

    @Override
    public String getContent() {
        return content;
    }

}
