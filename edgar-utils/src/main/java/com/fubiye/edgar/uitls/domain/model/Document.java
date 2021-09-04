package com.fubiye.edgar.uitls.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Document is the representation of a filing file
 */
@Data
@AllArgsConstructor
public class Document {
    private List<Page> pages;
}
