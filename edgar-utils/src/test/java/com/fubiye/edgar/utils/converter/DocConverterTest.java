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

package com.fubiye.edgar.utils.converter;

import com.fubiye.edgar.domain.model.reader.FilingDoc;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocConverterTest {

	private static final String FILE_PATH = "edgar/data/v211544_fwp.htm";

	private DocConverter converter;

	@BeforeEach
	public void setup() throws Exception {
		File file = FileUtils.toFile(this.getClass().getClassLoader().getResource(FILE_PATH));
		Document doc = Jsoup.parse(file, StandardCharsets.UTF_8.name());
		converter = new DocConverter(doc);
	}

	@Test
	@DisplayName("Converter should parse the generic information")
	public void testParseGeneralInfo() {
		FilingDoc filingDoc = converter.convert();
		assertEquals("10-K", filingDoc.getType());
		assertEquals("1", filingDoc.getSequence());
		assertEquals("d699698d10k.htm", filingDoc.getFilename());
		assertEquals("10-K", filingDoc.getDescription());
		assertEquals("10-K", filingDoc.getTitle());
		assertEquals(307, filingDoc.getText().size());
	}

	@Test
	public void testParseAllFiles() {

	}


}
