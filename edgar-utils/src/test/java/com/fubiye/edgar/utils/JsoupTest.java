package com.fubiye.edgar.utils;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

public class JsoupTest {
    // private static final String FILE_PATH = "D:\\data\\edgar\\sampling\\Archives\\edgar\\data\\894327\\000119312515333838\\d94487d424b5.htm";
    private static final String FILE_PATH = "D:\\data\\edgar\\sampling\\Archives\\edgar\\data\\894327\\000140720019000016\\dcent-dcmt10xdx051419.htm";
    @Test
    public void parseHtml() throws Exception{
			File file = new File(FILE_PATH);
			Document doc = Jsoup.parse(file, StandardCharsets.UTF_8.name());
			String text = doc.body().text();
			System.out.println(text);
    }    
}
