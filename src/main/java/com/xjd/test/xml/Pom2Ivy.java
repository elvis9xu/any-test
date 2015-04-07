package com.xjd.test.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Pom2Ivy {

	public static void main(String[] args) throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("D:/tmp/pom.xml"));
		Element root = doc.getRootElement();

		Element dependencies = root.element("dependencies");

		FileWriter writer = new FileWriter(new File("D:/tmp/ivy-dependencies.xml"));

		for (Object dependencyObj : dependencies.elements("dependency")) {
			Element dependency = (Element) dependencyObj;

			StringBuilder buf = new StringBuilder();
			buf.append("<dependency org=\"");
			buf.append(dependency.elementText("groupId"));
			buf.append("\" name=\"");
			buf.append(dependency.elementText("artifactId"));
			buf.append("\"  rev=\"");
			buf.append(dependency.elementText("version"));
			buf.append("\"   conf=\"");

			boolean b = false;
			Element scope = dependency.element("scope");
			if (scope != null && "test".equalsIgnoreCase(scope.getText())) {
				buf.append("test->default");
				buf.append("\" />");
				b = true;
//			} else if (scope != null && "provided".equalsIgnoreCase(scope.getText())) {

			} else {
				buf.append("zip->default");
				buf.append("\" />");
				b = true;
			}

			if (b) {
				writer.write(buf.toString());
				writer.write("\r\n");
				System.out.println(buf.toString());
			}
		}

		writer.close();
	}

}
