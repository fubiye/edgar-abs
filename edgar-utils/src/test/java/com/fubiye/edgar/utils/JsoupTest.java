package com.fubiye.edgar.utils;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class JsoupTest {
	private static final String FILE_PATH = "edgar/data/d699698d10k.htm";
    @Test
    public void parseHtml() throws Exception {
			File file = FileUtils.toFile(this.getClass().getClassLoader().getResource(FILE_PATH));
			Document doc = Jsoup.parse(file, StandardCharsets.UTF_8.name());
			String text = doc.body().text();
			System.out.println(text);
		}
}
