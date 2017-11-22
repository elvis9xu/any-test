package com.xjd.test.thumbnail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @author elvis.xu
 * @since 2017-08-14 14:48
 */
public class ImageTest {
	public static void main(String[] args) throws IOException {
		convert();
	}

	public static void convert() throws IOException {
		BufferedImage image = ImageIO.read(new File("/Users/XJD/Downloads/1501037510.jpg"));
//		Thumbnails.of("/Users/XJD/Downloads/1501037510.jpg")
		Thumbnails.of(image)
//				.width(image.getWidth())
				.width(1024)
				.outputQuality(0.75d)
				.outputFormat("jpg")
				.toFile(new File("/Users/XJD/Downloads/1501037510_1024_0.8.jpg"));
	}
}
