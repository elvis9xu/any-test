package com.xjd.test.thumbnail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

public class ThumbnailTest {

	public static void main(String[] args) throws IOException {
		Builder<File> builder = Thumbnails.of(new File("D:/tmp/source.jpg"));
		
		BufferedImage image = ImageIO.read(new File("D:/tmp/source.jpg"));
		
		builder.scale(3D).toFile(new File("D:/tmp/source_3.jpg"));
		
		Thumbnails.of(new File("D:/tmp/source.jpg")).scale(0.5D).toFile(new File("D:/tmp/source_0.5.jpg"));
		
		Thumbnails.of(new File("D:/tmp/source.jpg")).scale(2D).toFile(new File("D:/tmp/source_2.jpg"));
	}

}
