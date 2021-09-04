package com.fubiye.edgar.uitls.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Page {
    private List<Content> contents;
}
