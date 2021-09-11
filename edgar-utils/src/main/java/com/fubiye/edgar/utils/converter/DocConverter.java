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
import com.fubiye.edgar.domain.model.reader.content.Content;
import com.fubiye.edgar.domain.model.reader.content.FlatContent;
import com.fubiye.edgar.domain.model.reader.content.TableContent;
import com.fubiye.edgar.domain.model.reader.table.TblData;
import com.fubiye.edgar.domain.model.reader.table.TblHead;
import com.fubiye.edgar.domain.model.reader.table.TblRow;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DocConverter {

	private Document doc;

	public DocConverter(Document doc) {
		this.doc = doc;
	}

	public FilingDoc convert() {
		var builder = FilingDoc.builder();
		Elements currentElements = doc.select("document").first().select("type");
		builder.type(currentElements.first().ownText());
		currentElements = currentElements.select("sequence");
		builder.sequence(currentElements.first().ownText());
		currentElements = currentElements.select("filename");
		builder.filename(currentElements.first().ownText());
		if (null != currentElements.select("description").first()) {
			currentElements = currentElements.select("description");
			builder.description(currentElements.first().ownText());
		}
		currentElements = currentElements.select("text");
		builder.title(currentElements.select("title").first().ownText());
		Elements textEles = currentElements.first().children();

		List<Content> textContents = new ArrayList<>(textEles.size());
		currentElements.first().children().forEach(element -> {
			if ("table".equals(element.tagName())) {
				TblData tblData = TblData.builder().build();
				element.children().forEach(tblEle -> {
					if ("tbody".equals(tblEle.tagName())) {
						List<TblRow> rows = new ArrayList<>();
						tblEle.children().forEach(rowEle -> {
							List<String> columns = rowEle.children().stream().map(col -> col.wholeText()).collect(Collectors.toList());
							TblRow row = new TblRow(columns);
							rows.add(row);
						});
						tblData.setRows(rows);
					} else if ("thead".equals(tblEle.tagName())) {
						List<TblHead> heads = new ArrayList<>();
						tblEle.children().forEach(headEle -> {
							List<String> columns = headEle.children().stream().map(col -> col.wholeText()).collect(Collectors.toList());
							TblHead head = new TblHead(columns);
							heads.add(head);
						});
						tblData.setHeads(heads);
					}
					;
				});
				Content content = TableContent.builder().content(tblData).build();
				textContents.add(content);
			} else {
				Content content = FlatContent.builder().tag(element.tagName()).content(element.wholeText()).build();
				textContents.add(content);
			}
		});
		builder.text(textContents);
		return builder.build();
	}

}