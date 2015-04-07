package com.xjd.test.charset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

public class ASCII2Chinease {

	public static void main(String[] args) throws IOException {
		InputStreamReader r = new InputStreamReader(ASCII2Chinease.class.getResourceAsStream("test.txt"));
//		InputStreamReader r = new InputStreamReader(ASCII2Chinease.class.getResourceAsStream("asc.txt"));
		
		BufferedReader br = new BufferedReader(r);
		Writer w = new OutputStreamWriter(System.out, "UTF8");

		translate(br, w);

		br.close();
		w.flush();
	}

	public static void translate(Reader r, Writer w) throws IOException {
		int i, z;
		char c;
		String s;
		while ((i = r.read()) != -1) {
			c = (char) i;
			if (c == '\\') {
				i = r.read();
				if (i == -1) {
					w.write(c);
					break;
				} else if (i != 'u') {
					w.write(c);
					w.write(i);
					continue;
				} else { // 处理ASCII码
					s = "";
					for (z = 0; z < 4; z++) {
						i = r.read();
						if (i == -1) {
							throw new RuntimeException("内容不完整: \\u" + s);
						}
						s += (char) i;
					}
					i = Integer.parseInt(s, 16);
					w.write(i);
				}
			} else {
				w.write(i);
			}
		}
	}

}
