package com.xjd.test.time;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TimeTest {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
//		A a = new A();
//		a.setDate(new Date());
//		
//		ObjectMapper objMap = new ObjectMapper();
//		String s = objMap.writeValueAsString(a);
//		System.out.println(s);
		System.out.println(System.currentTimeMillis());
	}

	public static class A {
		private Date date;

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

	}

}
