package com.xjd.test.format;

import java.text.MessageFormat;

public class MessageFormatTest {

	public static void main(String[] args) {
		{
			String pattern = "hello {0}, welcome to {1}";
			
			MessageFormat format = new MessageFormat(pattern);
			String rt = format.format(new String[]{"xjd('good')", "xjd system"});
			
			System.out.println(rt);
		}
		{
			String pattern = "Not satisfy auth-expression {0}";
			
			MessageFormat format = new MessageFormat(pattern);
			String rt = format.format(new String[]{"any('HELLO')"});
			
			System.out.println(rt);
		}
	}

}
