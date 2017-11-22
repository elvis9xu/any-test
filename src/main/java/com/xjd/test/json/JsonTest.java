package com.xjd.test.json;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	public static ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		String txt = "{\"name\":\"hello\"}";
		String txt = "{\"code\":\"0012\",\"msg\":\"参数msgId不能为空\",\"time\":1433845785030}";

		Map s = objectMapper.readValue(txt, Map.class);

		System.out.println(s.getClass());

		String s1 = objectMapper.writeValueAsString(s);
		System.out.println(txt);
		System.out.println(s1);
		System.out.println(s.get("time").getClass());
	}

}
