package com.xjd.test.lombok;

import java.io.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvis.xu
 * @since 2016-01-11 21:33
 */
// @Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Slf4j
@Builder
public class TestBean {
	@NonNull
	private String name;
	private  int age;
	private String addr;

	@SneakyThrows(UnsupportedEncodingException.class)
	public String getUtf8Name() {
		return new String(name.getBytes(), "utf8");
	}

	@SneakyThrows
	public static void main(String[] args) {
		TestBean testBean = new TestBean("XXX", 20, "HAHAHAH");
		System.out.println(testBean.getName());
		System.out.println(testBean);

		testBean = TestBean.builder().name("XYZ").age(30).addr("Hello World!").build();
		System.out.println(testBean);

		val bean = new TestBean("TXY");
		System.out.println(bean);

		byte[] buf = new byte[]{1, 2, 3, 4, 5};
		@Cleanup InputStream in = new ByteArrayInputStream(buf);
		@Cleanup OutputStream out = new ByteArrayOutputStream();
		byte[] bs = new byte[1];
		int c = -1;
		while ((c = in.read(bs)) != -1) {
			out.write(bs, 0, c);
		}
	}
}
