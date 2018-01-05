package com.xjd.test.pdf.pdf2html;

/**
 * @author elvis.xu
 * @since 2017-12-18 18:08
 */
public class Pdf2HtmlEXUtil {

	/**
	 * 调用pdf2htmlEX将pdf文件转换为html文件
	 *
	 * @param command  调用exe的字符串
	 * @param pdfName  需要转换的pdf文件名称
	 * @param htmlName 生成的html文件名称
	 * @return
	 */
	public static boolean pdf2html(String command, String pdfName, String htmlName) {
		Runtime rt = Runtime.getRuntime();
		try {
			Process p = rt.exec(command);
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
			// kick off stderr
			errorGobbler.start();
			StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
			// kick off stdout
			outGobbler.start();
			int w = p.waitFor();
			System.out.println(w);
			int v = p.exitValue();
			System.out.println(v);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		pdf2html("pdf2htmlex /Users/XJD/Downloads/pdf2htmltest/test.pdf " +
						"test2.html", "test.pdf",
				"test2.html");
	}
}
