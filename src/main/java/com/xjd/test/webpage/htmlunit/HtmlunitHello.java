package com.xjd.test.webpage.htmlunit;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author elvis.xu
 * @since 2018-02-12 11:53
 */
public class HtmlunitHello {
	public static void main(String[] args) throws IOException {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		HtmlPage page = webClient.getPage("http://f.wozai4u.com/f/7HH4AD");

		System.out.println(page.asText());
	}
}