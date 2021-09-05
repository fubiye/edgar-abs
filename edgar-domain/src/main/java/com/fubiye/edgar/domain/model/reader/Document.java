package com.fubiye.edgar.domain.model.reader;

import java.util.List;

/**
 * Document is the representation of a filing file
 */

public record Document(List<Page> pages) {
    
}
