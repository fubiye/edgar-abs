package com.fubiye.edgar.domain.model.filing;

import com.fubiye.edgar.domain.model.reader.Document;

public record FilingFile(String filingId, String seq, String content, Document document) {
	
}
