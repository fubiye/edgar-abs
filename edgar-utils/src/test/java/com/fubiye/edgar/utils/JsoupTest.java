/*
 * MIT License
 *
 * Copyright (c) 2021 Fu, Biye
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.fubiye.edgar.utils;

import com.fubiye.edgar.domain.model.reader.FilingDoc;
import com.fubiye.edgar.domain.model.reader.FilingDoc.FilingDocBuilder;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class JsoupTest {
	private static final String FILE_PATH = "edgar/data/d699698d10k.htm";
    @Test
    public void parseHtml() throws Exception {
			File file = FileUtils.toFile(this.getClass().getClassLoader().getResource(FILE_PATH));
			Document doc = Jsoup.parse(file, StandardCharsets.UTF_8.name());
			FilingDocBuilder builder = FilingDoc.builder();
			Elements currentElements = doc.select("document").first().select("type");
			builder.type(currentElements.first().ownText());
			currentElements = currentElements.select("sequence");
			builder.sequence(currentElements.first().ownText());
			currentElements = currentElements.select("filename");
			builder.filename(currentElements.first().ownText());
			currentElements = currentElements.select("description");
			builder.description(currentElements.first().ownText());
			currentElements = currentElements.select("text");
			builder.title(currentElements.select("title").first().ownText());

			System.out.println(builder.build());
		}
}
